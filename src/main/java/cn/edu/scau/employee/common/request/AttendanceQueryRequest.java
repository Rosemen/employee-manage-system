package cn.edu.scau.employee.common.request;

import cn.edu.scau.common.constant.PageConstant;
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
@ApiModel(value = "AttendanceQueryRequest", description = "每日打卡记录查询request")
public class AttendanceQueryRequest {

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;

    @NotNull
    @ApiModelProperty(value = "年份")
    private PageConstant page;

}
