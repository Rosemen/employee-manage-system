package cn.edu.scau.employee.common.request;

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
@ApiModel(value = "BusinessTripAddRequest", description = "出差request")
public class BusinessTripAddRequest {

    @NotNull
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @NotNull
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @NotNull
    @ApiModelProperty(value = "出差地点")
    private String place;

    @ApiModelProperty(value = "备注")
    private String remark;
}
