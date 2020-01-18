package cn.edu.scau.employee.dao;

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

    public User findByUsername(String username) {
        return userExtendMapper.selectByUsername(username);
    }

    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    public int add(User user) {
        return userMapper.insert(user);
    }

    public int deleteByIds(List<Integer> ids) {
        return userExtendMapper.deleteByIds(ids);
    }

    public List<User> findByUsernameOrName(String username, String name) {
        return userExtendMapper.selectByUsernameOrName(username, name);
    }

    public List<User> findAll() {
        return userExtendMapper.selectAll();
    }
}
