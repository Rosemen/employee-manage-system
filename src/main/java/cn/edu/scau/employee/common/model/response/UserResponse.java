package cn.edu.scau.employee.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 18:10
 */
@Data
@ApiModel(value = "UserResponse", description = "用户请求响应类")
public class UserResponse {

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "员工详细信息id")
    private Long userDetailId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
