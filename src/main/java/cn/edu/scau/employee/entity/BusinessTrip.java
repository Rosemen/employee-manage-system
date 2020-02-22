package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 出差实体类
 */
@Data
public class BusinessTrip {

    private Long id;

    private Long empNo;

    private Date startTime;

    private Date endTime;

    private String place;

    private Integer status;

    private String auditor;

    private String disagreeReason;

    private Date auditingTime;

    private String remark;

}