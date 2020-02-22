package cn.edu.scau.employee.common.constant;

import lombok.Data;



/**
 * @author chen.jiale
 * @Description 系统常量
 * @date 2020/2/2 21:12
 */
@Data
public class EmpInfoConstant {

    /**
     * 最大员工数
     */
    public static final int MAX_NUMBER = 100000;

    /**
     * 员工编号后缀
     */
    public static final String EMP_INDEX = "emp_no_suffix";

    /**
     * 考勤查询月份维度
     */
    public static final int MONTH_RANGE = 1;

    /**
     * 考勤查询季度维度
     */
    public static final int QUARTER_RANGE = 2;

    /**
     * 上班时间
     */
    public static final String START_TIME = "09:00:00";

    /**
     * 下班时间
     */
    public static final String END_TIME = "18:00:00";
}
