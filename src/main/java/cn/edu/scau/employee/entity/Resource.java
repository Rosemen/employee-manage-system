package cn.edu.scau.employee.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen
 * @description 资源实体类
 * @date 2019/11/20
 */
@Data
public class Resource {

    private Long id;

    private String menuName;

    private String url;

    private Long parentId;

    private Date createDate;

    private Date updateDate;

    private String remark;

    private boolean leaf;
}