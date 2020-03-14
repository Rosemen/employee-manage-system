package cn.edu.scau.employee.common.model.request;

import cn.edu.scau.common.constant.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/03/07 11:27
 */
@ApiModel(value = "SalaryQueryRequest", description = "薪资查询request")
@Data
public class SalaryQueryRequest {

    @ApiModelProperty(value = "员工编号")
    private String empNo;

    @NotNull
    @ApiModelProperty(value = "年份")
    private Integer year;

    @NotNull
    @ApiModelProperty(value = "月份")
    private Integer month;

    @NotNull
    @ApiModelProperty(value = "分页参数")
    private PageConstant page;
}
