package cn.edu.scau.employee.config.logs;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.HttpUtil;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.common.annotation.Log;
import cn.edu.scau.employee.common.enums.ManageTypeEnum;
import cn.edu.scau.employee.common.response.UserResponse;
import cn.edu.scau.employee.common.util.MessageUtil;
import cn.edu.scau.employee.entity.ManageRecord;
import cn.edu.scau.employee.entity.User;
import cn.edu.scau.employee.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author chen.jiale
 * @Description 操作日志切面类
 * @date 2020/2/9 18:36
 */
@Component
@Aspect
public class ManageRecordAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;

    @Value("${spring.token.name}")
    private String tokenName;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Pointcut(value = "execution(* cn.edu.scau.employee.dao.*.*(..))")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()")
    public void afterReturn(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Class targetClass = methodSignature.getDeclaringType();
        Method method = methodSignature.getMethod();
        boolean flag = targetClass.isAnnotationPresent(Log.class) ||
                method.isAnnotationPresent(Log.class);
        if (flag) {
            HttpServletRequest request = HttpUtil.getHttpServletRequest();
            String token = request.getHeader(tokenName);
            CommonResult result = userService.findByToken(token);
            UserResponse user = (UserResponse) result.getData();
            Log annotation = !ObjectUtil.isEmpty(targetClass.getAnnotation(Log.class)) ?
                    (Log) targetClass.getAnnotation(Log.class) : method.getAnnotation(Log.class);
            ManageTypeEnum typeEnum = ManageTypeEnum.get(annotation.type());
            String manageType = !ObjectUtil.isEmpty(typeEnum) ? typeEnum.getMsg() : "";
            String table = annotation.table();
            String username = user.getUsername();
            ManageRecord record = ManageRecord.generate(username, manageType, table,
                    targetClass.getSimpleName(), method.getName());
            messageUtil.sendMessage(username, record, topic);
        }
    }


}
