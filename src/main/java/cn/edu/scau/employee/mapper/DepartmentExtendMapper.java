package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Department;

import java.util.List;

/**
 *
 * @author chen.jiale
 * @description 部门Mapper扩展接口
 * @date 2019/12/03
 */
public interface DepartmentExtendMapper {

    /**
     * 查询所有部门信息
     * @return
     */
    List<Department> selectAll();
}