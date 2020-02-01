package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.employee.common.request.RoleAddRequest;
import cn.edu.scau.employee.common.request.RoleQueryRequest;
import cn.edu.scau.employee.common.response.RoleResponse;
import cn.edu.scau.employee.dao.RoleDao;
import cn.edu.scau.employee.dao.RoleResourceDao;
import cn.edu.scau.employee.dao.UserDao;
import cn.edu.scau.employee.entity.Role;
import cn.edu.scau.employee.entity.RoleResource;
import cn.edu.scau.employee.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description 角色管理接口实现类
 * @date 2019/12/22 16:25
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public CommonResult findAll() {
        logger.info("RoleServiceImpl...  查询所有角色");
        List<Role> roles = roleDao.findAll();
        List<RoleResponse> roleResponses = roles.stream().map(role -> {
            RoleResponse roleResponse = ConvertUtil.convert(role, RoleResponse.class);
            return roleResponse;
        }).collect(Collectors.toList());
        return CommonResult.success(roleResponses);
    }

    @Override
    public CommonResult add(RoleAddRequest request) {
        logger.info("RoleServiceImpl...  添加角色, 角色信息: {}", request.toString());
        Role role = ConvertUtil.convert(request, Role.class);
        role.setCreateDate(DateUtil.currentDate());
        role.setUpdateDate(DateUtil.currentDate());
        Long roleId = roleDao.add(role);
        //批量插入角色资源列表
        List<Long> resourceIds = request.getResourceIds();
        if (null != resourceIds && resourceIds.size() > 0) {
            List<RoleResource> roleResources = request.getResourceIds().stream().map(resourceId -> {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);
                roleResource.setCreateTime(DateUtil.currentDate());
                roleResource.setUpdateTime(DateUtil.currentDate());
                return roleResource;
            }).collect(Collectors.toList());
            roleResourceDao.batchAdd(roleResources);
        }
        return CommonResult.success(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult update(Long id, RoleAddRequest request) {
        logger.info("RoleServiceImpl...  更新角色, 角色id: {}, 角色信息: {}", id, request.toString());
        Role role = ConvertUtil.convert(request, Role.class);
        role.setId(id);
        role.setUpdateDate(DateUtil.currentDate());
        roleDao.updateById(role);
        //先清除所有权限,再添加新权限
        List<Long> resourceIds = request.getResourceIds();
        if (null != resourceIds && resourceIds.size() > 0) {
            roleResourceDao.deleteByRoleIds(Collections.singletonList(id));
            List<RoleResource> roleResources = resourceIds.stream().map(resourceId -> {
                RoleResource roleResource = new RoleResource();
                roleResource.setResourceId(resourceId);
                roleResource.setRoleId(id);
                roleResource.setCreateTime(DateUtil.currentDate());
                roleResource.setUpdateTime(DateUtil.currentDate());
                return roleResource;
            }).collect(Collectors.toList());
            roleResourceDao.batchAdd(roleResources);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult findByName(RoleQueryRequest request) {
        logger.info("RoleServiceImpl...  分页查询角色, 请求参数: {}", request.toString());
        PageHelper.startPage(request.getPage().getCurrentPage(), request.getPage().getPageSize());
        List<Role> roles = roleDao.findByName(request.getName());
        List<RoleResponse> responses = roles.stream().map(role -> {
            RoleResponse roleResponse = ConvertUtil.convert(role, RoleResponse.class);
            List<Long> resourceIds = roleResourceDao.findResourceIdsByRoleId(role.getId());
            roleResponse.setResourceIds(resourceIds);
            return roleResponse;
        }).collect(Collectors.toList());
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        logger.info("RoleServiceImpl...  删除角色, 角色id列表: {}", Arrays.toString(ids.toArray()));
        int rows = roleDao.deleteByIds(ids);
        //删除角色绑定的资源
        roleResourceDao.deleteByRoleIds(ids);
        //更新对应用户的角色信息,0:无角色
        userDao.updateByRoleIds(0L, ids);
        return CommonResult.success(rows);
    }
}
