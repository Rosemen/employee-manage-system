package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 22:29
 */
@Component
public class ResourceDao {

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource selectById(Integer resourceId) {
        return resourceMapper.selectById(resourceId);
    }
}
