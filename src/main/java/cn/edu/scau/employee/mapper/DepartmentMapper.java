package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Department;

/**
 *
 * @author chen.jiale
 * @description 部门Mapper接口
 * @date 2019/12/03
 */
public interface DepartmentMapper {
    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加部门
     *
     * @param record
     * @return
     */
    int insert(Department record);

    /**
     * 查询部门
     *
     * @param id
     * @return
     */
    Department selectById(Long id);

    /**
     * 修改部门
     *
     * @param record
     * @return
     */
    int updateById(Department record);
}