package cn.edu.scau.employee.common.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/03/14 22:38
 */
@Data
public class SalaryExcelItem implements Serializable {

    private static final long serialVersionUID = -2681990221426393702L;

    @ExcelProperty(index = 0, value = "工号")
    private String empNo;

    @ExcelProperty(index = 1, value = "姓名")
    private String name;

    @ExcelProperty(index = 2, value = "部门")
    private String dept;

    @ExcelProperty(index = 3, value = "实发工资")
    private Double actualSalary;

    @ExcelProperty(index = 4, value = "基本工资")
    private Double baseSalary;

    @ExcelProperty(index = 5, value = "住房补贴")
    private Double housingSubsidies;

    @ExcelProperty(index = 6, value = "餐补")
    private Double mealAllowance;

    @ExcelProperty(index = 7, value = "加班补贴")
    private Double overtimeAllowance;

    @ExcelProperty(index = 8, value = "其他补贴")
    private Double otherAllowance;

    @ExcelProperty(index = 9, value = "缺勤扣款")
    private Double fromAbsences;

    @ExcelProperty(index = 10, value = "公积金")
    private Double housingProvidentFund;

    @ExcelProperty(index = 11, value = "发放时间")
    private Date paymentTime;


}
