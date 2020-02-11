package cn.edu.scau.employee.service;


import cn.edu.scau.employee.entity.Resource;

import java.util.List;


/**
 * @author chen.jiale
 * @description shiro专用, 解决shiro与aop冲突
 * @date 2019/11/20 10:53
 */
public interface ShiroResourceService {

    /**
     * 查询资源
     *
     * @param roleId
     * @return
     */
    List<Resource> findByRoleId(Long roleId);
}
