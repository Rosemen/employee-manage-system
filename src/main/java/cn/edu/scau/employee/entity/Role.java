package cn.edu.scau.employee.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author chen
 * @description 角色实体类
 * @date 2019/11/16
 */
@Data
public class Role{

    private Integer id;

    private String name;

    private Date createDate;

    private Date updateDate;

    private String remark;


}