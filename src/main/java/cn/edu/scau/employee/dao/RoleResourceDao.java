package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.common.annotation.Log;
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

    @Log(table = "role_resource", type = 2)
    public int deleteByRoleIds(List<Long> roleIds) {
        return roleResourceMapper.deleteByRoleIds(roleIds);
    }

    @Log(table = "role_resource", type = 1)
    public int batchAdd(List<RoleResource> roleResources) {
        return roleResourceMapper.batchInsert(roleResources);
    }

    public List<Long> findResourceIdsByRoleId(Long roleId) {
        return roleResourceMapper.selectResourceIdsByRoleId(roleId);
    }

    public List<RoleResource> findByRoleId(Long id) {
        return roleResourceMapper.selectByRoleId(id);
    }

    @Log(table = "role_resource", type = 1)
    public int add(RoleResource roleResource) {
        return roleResourceMapper.insert(roleResource);
    }

    @Log(table = "role_resource", type = 2)
    public int deleteByResourceIds(List<Long> resourceIds) {
        return roleResourceMapper.deleteByResourceIds(resourceIds);
    }
}
