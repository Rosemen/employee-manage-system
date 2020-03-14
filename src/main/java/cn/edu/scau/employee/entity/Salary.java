package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author chen.jiale
 * @date 2020/03/07 21:46:00
 * @description 工资实体
 */
@Data
public class Salary {

    private Long id;

    private String empNo;

    private Double baseSalary;

    private Double actualSalary;

    private Double housingSubsidies;

    private Double mealAllowance;

    private Double overtimeAllowance;

    private Double otherAllowance;

    private Double fromAbsences;

    private Double housingProvidentFund;

    private Date paymentTime;

    private Date updateTime;

}