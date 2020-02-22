package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/2 16:59
 */
@Data
@ApiModel(value = "UserDetailAddRequest", description = "用户信息请求响应类")
public class UserDetailAddRequest {

    @NotNull
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotNull
    @ApiModelProperty(value = "性别")
    private Integer gender;

    @NotNull
    @ApiModelProperty(value = "生日")
    private Date birthday;

    @NotNull
    @ApiModelProperty(value = "入职日期")
    private Date hiredate;

    @NotNull
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotNull
    @ApiModelProperty(value = "住址")
    private String address;

    @NotNull
    @ApiModelProperty(value = "照片")
    private String photo;

    @NotNull
    @ApiModelProperty(value = "学历")
    private String education;

    @NotNull
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @NotNull
    @ApiModelProperty(value = "员工编号")
    private Long empNo;

    @NotNull
    @ApiModelProperty(value = "职位")
    private String position;

}
