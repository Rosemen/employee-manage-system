package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 11:27
 */
@ApiModel(value = "UserLoginRequest", description = "用户登录请求参数封装类")
@Data
public class UserLoginRequest {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}