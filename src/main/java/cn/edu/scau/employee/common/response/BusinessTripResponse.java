package cn.edu.scau.employee.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/21 23:59
 */
@Data
@ApiModel(value = "BusinessTripResponse", description = "出差响应类")
public class BusinessTripResponse {

    @ApiModelProperty(value = "员工编号")
    private Long empNo;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "处理状态")
    private String auditorStatus;

    @ApiModelProperty(value = "审核人")
    private String auditor;

    @ApiModelProperty(value = "未同意原因")
    private String disagreeReason;

}
