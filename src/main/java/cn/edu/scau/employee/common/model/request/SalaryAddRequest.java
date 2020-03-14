package cn.edu.scau.employee.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/03/07 11:27
 */
@ApiModel(value = "SalaryAddRequest", description = "薪资添加request")
@Data
public class SalaryAddRequest {

    @NotNull
    @ApiModelProperty(value = "工号")
    private String empNo;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "基本工资")
    private double baseSalary;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "实发工资")
    private Double actualSalary;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "住房补贴")
    private Double housingSubsidies;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "餐补")
    private Double mealAllowance;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "加班补贴")
    private Double overtimeAllowance;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "其他补贴")
    private Double otherAllowance;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "缺勤捐款")
    private Double fromAbsences;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "住房公积金")
    private Double housingProvidentFund;


}
