package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/22 15:36
 */
@Data
@ApiModel(value = "ResourceAddRequest", description = "资源添加request")
public class ResourceAddRequest {

    @NotNull
    @ApiModelProperty(value = "菜单")
    private String menuName;

    @NotNull
    @ApiModelProperty(value = "资源url")
    private String url;

    @NotNull
    @ApiModelProperty(value = "父资源id")
    private Long parentId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否叶子节点")
    private boolean leaf;

}
