package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author chen.jiale
 * @description 部门实体类
 * @date  2019/12/03
 */
@Data
public class Department{

    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private String remark;
}