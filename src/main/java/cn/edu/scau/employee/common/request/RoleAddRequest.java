package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @author chen.jiale
 * @Description 角色添加请求参数封装类
 * @date 2019/12/22 11:24
 */
@ApiModel(value = "RoleAddRequest", description = "角色添加请求")
@Data
public class RoleAddRequest {

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "资源id列表")
    private List<Long> resourceIds;

    @ApiModelProperty(value = "备注")
    private String remark;
}
