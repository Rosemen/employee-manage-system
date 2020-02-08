package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 每日出勤表
 */
@Data
public class Attendance {

    private Long id;

    private Long empNo;

    private Date startTime;

    private Date endTime;

    private boolean late;

    private boolean leaveEarly;
}