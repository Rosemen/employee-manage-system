package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @author chen
 * @description 角色资源关联类
 * @date 2019/11/20
 *
 */
@Data
public class RoleResource{

    private Long roleId;

    private Long resourceId;

    private Date createTime;

    private Date updateTime;

    private String remark;
}