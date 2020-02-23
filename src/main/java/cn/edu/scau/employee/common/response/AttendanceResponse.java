package cn.edu.scau.employee.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/23 10:23
 */
@Data
@ApiModel(value = "AttendanceResponse", description = "每日打卡记录响应类")
public class AttendanceResponse {

    @ApiModelProperty(value = "员工编号")
    private Long empNo;

    @ApiModelProperty(value = "签到时间")
    private Date startTime;

    @ApiModelProperty(value = "签退时间")
    private Date endTime;

    @ApiModelProperty(value = "是否迟到")
    private boolean late;

    @ApiModelProperty(value = "是否早退")
    private boolean leaveEarly;
}
