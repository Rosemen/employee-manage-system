package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.RoleResource;
import cn.edu.scau.employee.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description shiro专用, 解决shiro与aop冲突
 * @date 2019/12/22 22:36
 */
@Component
public class ShiroRoleResourceDao {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public List<RoleResource> selectByRoleId(Long roleId) {
        return roleResourceMapper.selectByRoleId(roleId);
    }
}
