package cn.edu.scau.employee.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chen.jiale
 * @Description 考勤统计响应类
 * @date 2019/12/30 23:59
 */
@Data
@ApiModel(value = "AttendCountResponse", description = "考勤统计响应类")
public class AttendCountResponse {

    @ApiModelProperty(value = "员工编号")
    private Long empNo;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "员工部门")
    private String dept;

    @ApiModelProperty(value = "应出席天数")
    private Integer attendDays;

    @ApiModelProperty(value = "实际出席天数")
    private Integer actualAttendDays;

    @ApiModelProperty(value = "迟到天数")
    private Integer lateDays;

    @ApiModelProperty(value = "早退天数")
    private Integer earlyDays;

    @ApiModelProperty(value = "旷工天数")
    private Integer skiDays;

    @ApiModelProperty(value = "出差天数")
    private Integer businessDays;

    @ApiModelProperty(value = "请假天数")
    private Integer leaveDays;
}
