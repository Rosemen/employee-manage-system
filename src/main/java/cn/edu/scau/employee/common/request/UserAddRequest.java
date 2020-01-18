package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 11:28
 */
@Data
@ApiModel(value = "UserAddDto", description = "用户添加Dto")
public class UserAddRequest {

    @ApiModelProperty(value = "工号")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "入职日期")
    private Date hiredate;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "照片")
    private String photo;

    @ApiModelProperty(value = "部门id")
    private Integer deptId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;
}
