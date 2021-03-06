package cn.edu.scau.employee.common.model.request;

import cn.edu.scau.common.constant.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/24 10:16
 */
@Data
@ApiModel(value = "RoleQueryRequest", description = "角色查询请求类")
public class RoleQueryRequest {

    @ApiModelProperty(value = "角色名")
    private String name;

    @NotNull
    @ApiModelProperty(value = "分页信息")
    private PageConstant page;
}
