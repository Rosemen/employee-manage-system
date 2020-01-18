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

    private Integer id;

    private String username;

    private String name;

    private Integer gender;

    private String password;

    private Date birthday;

    private Date hiredate;

    private String phone;

    private String email;

    private String address;

    private String photo;

    private String education;

    private Integer deptId;

    private Integer roleId;
}