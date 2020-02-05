package cn.edu.scau.employee.entity;

import java.util.Date;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 每日出勤表
 */
public class Attendence {

    private Long id;

    private Long empNo;

    private Date startTime;

    private Date endTime;

    private Boolean late;

    private Boolean leaveEarly;
}