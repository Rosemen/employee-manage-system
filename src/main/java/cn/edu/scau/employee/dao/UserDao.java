package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.common.annotation.Log;
import cn.edu.scau.employee.common.enums.ManageTypeEnum;
import cn.edu.scau.employee.entity.User;
import cn.edu.scau.employee.mapper.UserExtendMapper;
import cn.edu.scau.employee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 16:33
 */
@Component
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtendMapper userExtendMapper;

    @Log(table = "t_user", type = 3)
    public int updateById(User user) {
        int rows = userMapper.updateById(user);
        return rows;
    }

    @Log(table = "t_user", type = 1)
    public Long add(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Log(table = "t_user", type = 2)
    public int deleteByIds(List<Long> ids) {
        return userExtendMapper.deleteByIds(ids);
    }

    public List<User> findByUsername(String username) {
        return userExtendMapper.selectByUsername(username);
    }

    public List<User> findAll() {
        return userExtendMapper.selectAll();
    }

    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    @Log(table = "t_user", type = 3)
    public int updateByRoleIds(Long roleId, List<Long> roleIds) {
        return userExtendMapper.updateByRoleIds(roleId, roleIds);
    }
}
