package cn.edu.scau.employee.dao.shiro;

import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author chen.jiale
 * @Description shiro专用, 解决shiro与aop冲突
 * @date 2019/12/22 22:29
 */
@Component
public class ShiroResourceDao {

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource findById(Long resourceId) {
        return resourceMapper.selectById(resourceId);
    }

}
