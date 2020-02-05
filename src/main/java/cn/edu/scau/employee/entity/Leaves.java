package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 请假实体类
 */
@Data
public class Leaves {

    private Long id;

    private Long empNo;

    private Integer type;

    private Date startTime;

    private Date endTime;

    private String remark;

}