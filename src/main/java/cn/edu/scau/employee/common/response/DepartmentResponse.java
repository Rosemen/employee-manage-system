package cn.edu.scau.employee.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/30 23:59
 */
@Data
@ApiModel(value = "DepartmentResponse", description = "部门请求响应类")
public class DepartmentResponse {

    @ApiModelProperty(value = "部门id")
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;
}
