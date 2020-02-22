package cn.edu.scau.employee.common.util;

import cn.edu.scau.employee.config.exception.EmployeeException;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author chen.jiale
 * @Description 参数校验工具
 * @date 2020/2/22 16:04
 */
public class ValidatorUtil {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    private static Validator validator;

    static {
        validator = Validation.byProvider(HibernateValidator.class)
                .configure().failFast(true).buildValidatorFactory()
                .getValidator();
    }

    public static <T> void validate(T obj) throws Exception {
        if (null == obj) {
            String className = obj.getClass().getSimpleName();
            logger.error("参数校验失败, {}不能为Null");
            throw new EmployeeException("参数校验失败," + className + "不能为Null");
        }
        Set<ConstraintViolation<T>> resultSet = validator.validate(obj);
        doValidate(resultSet);
    }

    public static <T> void validate(T obj, Class group) throws Exception {
        if (null == obj) {
            String className = obj.getClass().getSimpleName();
            logger.error("参数校验失败, {}不能为Null");
            throw new EmployeeException("参数校验失败," + className + "不能为Null");
        }
        Set<ConstraintViolation<T>> resultSet = validator.validate(obj, group);
        doValidate(resultSet);
    }

    private static <T> void doValidate(Set<ConstraintViolation<T>> resultSet) throws Exception {
        if (!resultSet.isEmpty()) {
            StringBuilder errorMsg = new StringBuilder();
            resultSet.stream().forEach(result -> {
                String error = result.getMessage();
                String fieldName = result.getPropertyPath().toString();
                logger.error("参数校验失败: {}{}", fieldName, error);
                errorMsg.append(fieldName + error + ",");
            });
            throw new EmployeeException(errorMsg.toString().substring(0, errorMsg.length() - 1));
        }
    }
}
