package cn.edu.scau.employee.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 11:28
 */
@Data
@ApiModel(value = "UserAddRequest", description = "用户添加request")
public class UserAddRequest {

    @NotNull
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotNull
    @ApiModelProperty(value = "密码")
    private String password = "123456";

    @NotNull
    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
