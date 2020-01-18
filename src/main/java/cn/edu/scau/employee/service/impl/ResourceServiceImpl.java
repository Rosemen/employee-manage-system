package cn.edu.scau.employee.service.impl;

import cn.edu.scau.employee.dao.ResourceDao;
import cn.edu.scau.employee.dao.RoleResourceDao;
import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.entity.RoleResource;
import cn.edu.scau.employee.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 16:27
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> findByRoleId(Integer roleId) {
        List<RoleResource> roleResources = roleResourceDao.selectByRoleId(roleId);
        List<Resource> resources = roleResources.stream().map(roleResource -> {
            Resource resource = resourceDao.selectById(roleResource.getResourceId());
            return resource;
        }).collect(Collectors.toList());
        return resources;
    }
}
