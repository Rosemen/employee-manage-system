package cn.edu.scau.employee.common.request;

import cn.edu.scau.common.constant.PageConstant;
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
@ApiModel(value = "ResourceQueryRequest", description = "资源查询request")
public class ResourceQueryRequest {

    @ApiModelProperty(value = "菜单名")
    private String menuName;

    @NotNull
    @ApiModelProperty(value = "分页信息")
    private PageConstant page;
}
