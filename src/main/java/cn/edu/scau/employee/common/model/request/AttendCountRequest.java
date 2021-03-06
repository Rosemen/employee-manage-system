package cn.edu.scau.employee.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/5 14:52
 */
@Data
@ApiModel(value = "AttendCountRequest", description = "考勤统计request")
public class AttendCountRequest {

    @NotNull
    @ApiModelProperty(value = "查询纬度 1:月份 2:季度")
    private Integer range;

    @NotNull
    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;

    @ApiModelProperty(value = "季度")
    private Integer quarter;

}
