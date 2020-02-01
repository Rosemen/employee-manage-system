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

    /**
     * 根据id列表删除部门信息
     *
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);

    /**
     * 根据部门名模糊查询
     *
     * @param name
     * @return
     */
    List<Department> findByName(String name);
}