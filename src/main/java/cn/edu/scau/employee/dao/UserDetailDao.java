package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.mapper.UserDetailExtendMapper;
import cn.edu.scau.employee.mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/21 14:31
 */
@Component
public class UserDetailDao {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private UserDetailExtendMapper userDetailExtendMapper;

    public int deleteByIds(List<Long> userDetailIds) {
        return userDetailExtendMapper.deleteByIds(userDetailIds);
    }

    public int updateByDeptIds(List<Long> deptIds) {
        //0:不属于任何部门
        return userDetailExtendMapper.updateByDeptIds(0L, deptIds);
    }
}
