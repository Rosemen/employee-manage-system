package cn.edu.scau.employee.common.annotation;

import java.lang.annotation.*;

/**
 * @author chen.jiale
 * @date 2020/02/09
 * @description 操作日志注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 表名
     *
     * @return
     */
    String table() default "";

    /**
     * 操作类型 1:插入 2:删除 3:修改
     *
     * @return
     */
    int type() default 0;

}
