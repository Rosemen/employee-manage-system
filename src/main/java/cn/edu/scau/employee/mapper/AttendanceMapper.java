package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Attendance;


public interface AttendanceMapper {

    /**
     * 删除每日考勤记录
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 插入每日考勤记录
     *
     * @param record
     * @return
     */
    int insert(Attendance record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Attendance selectById(Long id);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateById(Attendance record);

}