package cn.edu.scau.employee.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/26 0:16
 */
@Data
@ApiModel(value = "RoleResponse", description = "角色请求响应类")
public class RoleResponse {

    @ApiModelProperty(value = "角色id")
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
