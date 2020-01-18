package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Role;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 角色扩展Mapper
 * @date 2019/12/22 21:13
 */
public interface RoleExtendMapper {

    /**
     * 获取所有角色信息
     *
     * @return
     */
    List<Role> selectAll();
}
