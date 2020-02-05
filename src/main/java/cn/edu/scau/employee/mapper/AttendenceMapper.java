package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Attendence;

public interface AttendenceMapper {

    int deleteById(Long id);

    int insert(Attendence record);

    Attendence selectById(Long id);

    int updateById(Attendence record);

}