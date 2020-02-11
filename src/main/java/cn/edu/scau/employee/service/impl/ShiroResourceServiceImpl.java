package cn.edu.scau.employee.service.impl;

import cn.edu.scau.employee.dao.ShiroResourceDao;
import cn.edu.scau.employee.dao.ShiroRoleResourceDao;
import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.entity.RoleResource;
import cn.edu.scau.employee.service.ShiroResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description shiro专用, 解决aop与shiro冲突
 * @date 2019/12/22 16:27
 */
@Service
public class ShiroResourceServiceImpl implements ShiroResourceService {

    @Autowired
    private ShiroResourceDao resourceDao;

    @Autowired
    private ShiroRoleResourceDao roleResourceDao;

    @Override
    public List<Resource> findByRoleId(Long roleId) {
        List<RoleResource> roleResources = roleResourceDao.selectByRoleId(roleId);
        List<Resource> resources = roleResources.stream().map(roleResource -> {
            Resource resource = resourceDao.findById(roleResource.getResourceId());
            return resource;
        }).collect(Collectors.toList());
        return resources;
    }
}
