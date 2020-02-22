package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.common.annotation.Log;
import cn.edu.scau.employee.entity.Attendance;
import cn.edu.scau.employee.mapper.AttendanceExtendMapper;
import cn.edu.scau.employee.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/5 15:33
 */
@Component
public class AttendanceDao {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private AttendanceExtendMapper attendanceExtendMapper;

    public List<Attendance> findByEmpNo(Long empNo) {
        return attendanceExtendMapper.selectByEmpNo(empNo);
    }

    public List<Attendance> findByEmpNoAndMonth(Integer year, Integer month, Long empNo) {
        return attendanceExtendMapper.selectByEmpNoAndMonth(year, month, empNo);
    }

    public List<Attendance> findByEmpNoAndQuarter(Integer year, Integer quarter, Long empNo) {
        return attendanceExtendMapper.selectByEmpNoAndQuarter(year, quarter, empNo);
    }

    public Attendance findByEmpNoAndStartTime(Long empNo, String startTime) {
        return attendanceExtendMapper.selectByEmpNoAndStartTime(empNo, startTime);
    }

    @Log(table = "attendance", type = 3)
    public int updateById(Attendance attendance) {
        return attendanceMapper.updateById(attendance);
    }

    @Log(table = "attendance", type = 1)
    public int add(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }
}
