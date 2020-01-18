package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Role;

import java.util.List;

/**
 *
 * @author chen
 * @description 角色Mapper
 * @date 2019/11/16
 */
public interface RoleMapper {
    /**
     * 添加角色
     *
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 更新角色
     *
     * @param record
     * @return
     */
    int updateById(Role record);

    /**
     * 查询角色
     *
     * @param id
     * @return
     */
    Role selectById(Integer id);
}