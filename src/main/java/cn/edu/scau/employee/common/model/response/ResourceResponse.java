package cn.edu.scau.employee.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/22 16:45
 */
@Data
@ApiModel(value = "ResourceResponse", description = "资源请求响应类")
public class ResourceResponse {

    @ApiModelProperty(value = "资源id")
    private Long id;

    @ApiModelProperty(value = "菜单")
    private String menuName;

    @ApiModelProperty(value = "资源url")
    private String url;

    @ApiModelProperty(value = "父资源id")
    private Long parentId;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "子资源列表")
    private List<ResourceResponse> children;

}
