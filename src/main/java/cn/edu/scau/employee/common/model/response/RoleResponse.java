package cn.edu.scau.employee.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/26 0:16
 */
@Data
@ApiModel(value = "RoleResponse", description = "角色请求响应类")
public class RoleResponse {

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "资源id列表")
    private List<Long> resourceIds;


}
