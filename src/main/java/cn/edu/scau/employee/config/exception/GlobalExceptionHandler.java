package cn.edu.scau.employee.config.exception;

import cn.edu.scau.common.enums.ResponseEnum;
import cn.edu.scau.common.result.CommonResult;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chen.jiale
 * @description 异常处理器
 * @date 2019/11/11 16:45
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {UnauthenticatedException.class, CredentialsException.class})
    public CommonResult loginHandler(Exception ex) {
        logger.error(ResponseEnum.NO_LOGIN.getMsg(), ex);
        return CommonResult.error(ResponseEnum.NO_LOGIN.getMsg());
    }

    @ExceptionHandler(value = {AuthorizationException.class})
    public CommonResult permissionHandler(Exception ex) {
        logger.error(ResponseEnum.NO_PERMISSION.getMsg(), ex);
        return CommonResult.error(ResponseEnum.NO_PERMISSION.getCode(),
                ResponseEnum.NO_PERMISSION.getMsg());
    }

    @ExceptionHandler(value = {EmployeeException.class})
    public CommonResult employeeExceptionHandler(Exception ex) {
        logger.error(ResponseEnum.ERROR.getMsg(), ex);
        return CommonResult.error(ResponseEnum.ERROR.getCode(),
                ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public CommonResult errorHandler(Exception ex) {
        logger.error(ResponseEnum.ERROR.getMsg(), ex);
        return CommonResult.error(ResponseEnum.ERROR.getMsg());
    }
}
