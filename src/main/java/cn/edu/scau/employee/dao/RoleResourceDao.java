package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.RoleResource;
import cn.edu.scau.employee.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 22:36
 */
@Component
public class RoleResourceDao {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public List<RoleResource> selectByRoleId(Long roleId) {
        return roleResourceMapper.selectByRoleId(roleId);
    }

    public int deleteByRoleIds(List<Long> roleIds) {
        return roleResourceMapper.deleteByRoleIds(roleIds);
    }

    public int batchAdd(List<RoleResource> roleResources) {
        return roleResourceMapper.batchInsert(roleResources);
    }

    public List<Long> findResourceIdsByRoleId(Long roleId) {
        return roleResourceMapper.selectResourceIdsByRoleId(roleId);
    }

    public List<RoleResource> findByRoleId(Long id) {
        return roleResourceMapper.selectByRoleId(id);
    }

    public int add(RoleResource roleResource) {
        return roleResourceMapper.insert(roleResource);
    }

    public int deleteByResourceIds(List<Long> resourceIds) {
        return roleResourceMapper.deleteByResourceIds(resourceIds);
    }
}
