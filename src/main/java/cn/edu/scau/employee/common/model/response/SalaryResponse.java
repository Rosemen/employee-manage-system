package cn.edu.scau.employee.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description 薪资响应类
 * @date 2020/3/8 15:47
 */
@Data
@ApiModel(value = "SalaryResponse", description = "薪资响应类")
public class SalaryResponse {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "工号")
    private String empNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "基本工资")
    private double baseSalary;

    @ApiModelProperty(value = "实发工资")
    private Double actualSalary;

    @ApiModelProperty(value = "住房补贴")
    private Double housingSubsidies;

    @ApiModelProperty(value = "餐补")
    private Double mealAllowance;

    @ApiModelProperty(value = "加班补贴")
    private Double overtimeAllowance;

    @ApiModelProperty(value = "其他补贴")
    private Double otherAllowance;

    @ApiModelProperty(value = "缺勤捐款")
    private Double fromAbsences;

    @ApiModelProperty(value = "住房公积金")
    private Double housingProvidentFund;

    @ApiModelProperty(value = "发放时间")
    private Date paymentTime;

}
