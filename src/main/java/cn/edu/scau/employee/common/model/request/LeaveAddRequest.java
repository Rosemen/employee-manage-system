package cn.edu.scau.employee.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/19 22:48
 */
@Data
@ApiModel(value = "LeaveAddRequest", description = "请假request")
public class LeaveAddRequest {

    @NotNull
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @NotNull
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @NotNull
    @ApiModelProperty(value = "请假类型")
    private Integer type;

    @ApiModelProperty(value = "备注")
    private String remark;
}
