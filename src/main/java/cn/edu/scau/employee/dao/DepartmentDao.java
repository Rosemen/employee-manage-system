package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.Department;
import cn.edu.scau.employee.mapper.DepartmentExtendMapper;
import cn.edu.scau.employee.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/30 23:53
 */
@Component
public class DepartmentDao {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentExtendMapper departmentExtendMapper;

    public List<Department> findAll() {
        return departmentExtendMapper.selectAll();
    }
}
