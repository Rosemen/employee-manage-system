package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.Role;
import cn.edu.scau.employee.mapper.RoleExtendMapper;
import cn.edu.scau.employee.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 21:16
 */
@Component
public class RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleExtendMapper roleExtendMapper;

    public List<Role> findAll() {
        return roleExtendMapper.selectAll();
    }

    public List<Role> findByName(String name) {
        return roleExtendMapper.selectByName(name);
    }

    public int deleteByIds(List<Long> ids) {
        return roleExtendMapper.deleteByIds(ids);
    }

    public Long add(Role role) {
        roleMapper.insert(role);
        return role.getId();
    }

    public int updateById(Role role) {
        return roleMapper.updateById(role);
    }
}
