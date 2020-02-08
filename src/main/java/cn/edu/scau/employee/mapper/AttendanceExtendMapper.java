package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/5 22:02
 */
public interface AttendanceExtendMapper {

    /**
     * 根据工号查询
     *
     * @param empNo
     * @return
     */
    List<Attendance> selectByEmpNo(Long empNo);

    /**
     * 查询某个员工某年某月的每日考勤记录
     *
     * @param year
     * @param month
     * @param empNo
     * @return
     */
    List<Attendance> selectByEmpNoAndMonth(@Param("year") Integer year,
                                           @Param("month") Integer month,
                                           @Param("empNo") Long empNo);

    /**
     * 查询某个员工某年某个季度的每日考勤记录
     *
     * @param year
     * @param quarter
     * @param empNo
     * @return
     */
    List<Attendance> selectByEmpNoAndQuarter(@Param("year") Integer year,
                                             @Param("quarter") Integer quarter,
                                             @Param("empNo") Long empNo);

}
