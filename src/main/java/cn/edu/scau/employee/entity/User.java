package cn.edu.scau.employee.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen
 * @description 用户实体类
 * @date 2019/11/11
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private Long roleId;

    private Long userDetailId;

    private Date createTime;

    private Date updateTime;
}