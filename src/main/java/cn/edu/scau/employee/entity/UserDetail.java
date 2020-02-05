package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @date 2020/01/21
 * @description
 */
@Data
public class UserDetail {

    private Long id;

    private String name;

    private Integer gender;

    private Date birthday;

    private Date hiredate;

    private String phone;

    private String email;

    private String address;

    private String photo;

    private String education;

    private Long deptId;

    private Long empNo;

    private String position;
}