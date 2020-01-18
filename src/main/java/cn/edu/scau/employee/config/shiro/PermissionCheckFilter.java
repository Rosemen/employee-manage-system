package cn.edu.scau.employee.config.shiro;

import cn.edu.scau.common.enums.ResponseEnum;
import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.constant.HttpConstant;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author chen.jiale
 * @description 权限校验过滤器
 * @date 2019/11/19 17:02
 */
public class PermissionCheckFilter extends PermissionsAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        return SecurityUtils.getSubject().isPermitted(getPathWithinApplication(request));
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        CommonResult result = CommonResult.error(ResponseEnum.NO_PERMISSION.getCode(),
                ResponseEnum.NO_PERMISSION.getMsg());
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
