package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/5 14:52
 */
@Data
@ApiModel(value = "AttendQueryRequest", description = "考勤信息查询request")
public class AttendQueryRequest {

    @ApiModelProperty(value = "查询纬度 1:月份 2:季度")
    private Integer range;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;

    @ApiModelProperty(value = "季度")
    private Integer quarter;

}
