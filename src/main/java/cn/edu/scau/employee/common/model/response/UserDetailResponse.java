package cn.edu.scau.employee.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/2 16:59
 */
@Data
@ApiModel(value = "UserDetailResponse", description = "用户信息请求响应类")
public class UserDetailResponse {

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "入职日期")
    private Date hiredate;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "照片")
    private String photo;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "员工编号")
    private Long empNo;

    @ApiModelProperty(value = "职位")
    private String position;

}
