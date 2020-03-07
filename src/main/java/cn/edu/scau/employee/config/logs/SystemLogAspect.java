package cn.edu.scau.employee.config.logs;

import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.common.util.HttpUtil;
import cn.edu.scau.common.util.ObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author chen.jiale
 * @Description 系统日志切面类
 * @date 2020/2/9 16:51
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Pointcut(value = "execution(* cn.edu.scau.employee.controller.*.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void beforeEnter(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        Class targetClass = methodSignature.getDeclaringType();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        String ip = HttpUtil.getIpAddress();
        String requestURL = request.getRequestURL().toString();
        logger.info("[请求开始时间]:{}", DateUtil.now());
        logger.info("目标控制器]:{}", targetClass.getSimpleName());
        logger.info("[目标方法]:{}", method.getName());
        logger.info("[请求源IP]:{}", ip);
        logger.info("[请求URL]:{}", requestURL);
        logger.info("[请求参数]:{}", Arrays.toString(args));
    }

    @AfterReturning(value = "pointcut()", returning = "response")
    public void afterReturn(JoinPoint joinPoint, Object response) {
        logger.info("[请求结始时间]:{}", DateUtil.now());
        String result = !ObjectUtil.isEmpty(response)?response.toString():"";
        logger.info("[响应结果]:{}", result
        );
    }
}
