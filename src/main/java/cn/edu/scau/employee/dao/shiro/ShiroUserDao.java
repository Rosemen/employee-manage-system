package cn.edu.scau.employee.dao.shiro;

import cn.edu.scau.employee.entity.User;
import cn.edu.scau.employee.mapper.UserExtendMapper;
import cn.edu.scau.employee.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description shiro专用, 解决shiro与aop冲突
 * @date 2019/12/22 16:33
 */
@Component
public class ShiroUserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtendMapper userExtendMapper;

    public List<User> findByUsername(String username) {
        return userExtendMapper.selectByUsername(username);
    }

}
