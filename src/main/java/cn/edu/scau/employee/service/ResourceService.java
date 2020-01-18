package cn.edu.scau.employee.service;


import cn.edu.scau.employee.entity.Resource;

import java.util.List;


/**
 *
 * @author chen.jiale
 * @description 资源业务接口
 * @date 2019/11/20 10:53
 */
public interface ResourceService {

    /**
     * 查询资源
     *
     * @param roleId
     * @return
     */
    List<Resource> findByRoleId(Integer roleId);
}
