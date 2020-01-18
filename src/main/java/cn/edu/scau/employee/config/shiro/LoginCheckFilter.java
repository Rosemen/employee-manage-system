package cn.edu.scau.employee.config.shiro;

import cn.edu.scau.common.enums.ResponseEnum;
import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.common.constant.HttpConstant;
import cn.edu.scau.employee.dao.TokenDao;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 自定义登录认证filter
 *
 * @author chen.jiale
 * @date 2019/11/9 17:03
 */
public class LoginCheckFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);

    /**
     * 判断请求是否已经登录过，使用自定义token校验
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest res = (HttpServletRequest) request;
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        TokenDao tokenDao = factory.getBean(TokenDao.class);
        String token = res.getHeader(tokenDao.getTokenName());
        if (ObjectUtil.isEmpty(token)) {
            return false;
        }
        return tokenDao.checkToken(token);
    }

    /**
     * 请求认证失败后执行这个方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        CommonResult result = CommonResult.error(ResponseEnum.NO_LOGIN.getCode(),
                ResponseEnum.NO_LOGIN.getMsg());
        String json = JSON.toJSONString(result);
        HttpServletResponse res = (HttpServletResponse) response;
        res.setCharacterEncoding(HttpConstant.CHARACTER_ENCODING);
        res.setContentType(HttpConstant.CONTENT_TYPE);
        PrintWriter out = res.getWriter();
        out.println(json);
        out.close();
        return false;
    }
}
