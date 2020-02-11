package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.common.request.ResourceAddRequest;
import cn.edu.scau.employee.common.request.ResourceQueryRequest;
import cn.edu.scau.employee.common.response.ResourceResponse;
import cn.edu.scau.employee.dao.ResourceDao;
import cn.edu.scau.employee.dao.RoleResourceDao;
import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.entity.RoleResource;
import cn.edu.scau.employee.service.ResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 16:27
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> findByRoleId(Long roleId) {
        List<RoleResource> roleResources = roleResourceDao.selectByRoleId(roleId);
        List<Resource> resources = roleResources.stream().map(roleResource -> {
            Resource resource = resourceDao.findById(roleResource.getResourceId());
            return resource;
        }).collect(Collectors.toList());
        return resources;
    }

    @Override
    public CommonResult findByParentId(Long parentId) {
        List<ResourceResponse> responses = this.getResourceResponses(parentId);
        return CommonResult.success(responses);
    }

    @Override
    public CommonResult findByName(ResourceQueryRequest request) {
        PageHelper.startPage(request.getPage().getCurrentPage(),
                request.getPage().getPageSize());
        List<Resource> resources = resourceDao.findByName(request.getMenuName());
        List<ResourceResponse> responses = resources.stream().map(resource -> {
            ResourceResponse response = ConvertUtil.convert(resource, ResourceResponse.class);
            return response;
        }).collect(Collectors.toList());
        PageInfo<Resource> pageInfo = new PageInfo<>(resources);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult add(ResourceAddRequest request) {
        //设置父节点为非叶子节点
        Date currentDate = DateUtil.currentDate();
        Long parentId = request.getParentId();
        Resource parentResource = resourceDao.findById(parentId);
        if (!ObjectUtil.isEmpty(parentResource)) {
            parentResource.setLeaf(false);
            parentResource.setUpdateDate(currentDate);
            resourceDao.updateById(parentResource);
        }
        //添加资源
        int num = resourceDao.getSubResourceNum(parentId);
        Resource resource = ConvertUtil.convert(request, Resource.class);
        resource.setId(parentId * 10 + num + 1);
        resource.setCreateDate(currentDate);
        resource.setUpdateDate(currentDate);
        resource.setLeaf(true);
        Long resourceId = resourceDao.add(resource);
        return CommonResult.success(resourceId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        //获取子资源id列表
        List<Long> subIds = resourceDao.findIdByParentIds(ids);
        //剔除重复id
        if (subIds != null && subIds.size() > 0) {
            ids.removeAll(subIds);
            ids.addAll(subIds);
        }
        //删除资源
        int rows = resourceDao.deleteByIds(ids);
        //删除角色资源记录
        roleResourceDao.deleteByResourceIds(ids);
        return CommonResult.success(rows);
    }

    @Override
    public CommonResult findAll() {
        List<Resource> resources = resourceDao.findAll();
        List<ResourceResponse> responses = resources.stream().map(resource -> {
            return ConvertUtil.convert(resource, ResourceResponse.class);
        }).collect(Collectors.toList());
        return CommonResult.success(responses);
    }

    @Override
    public CommonResult update(Long id, ResourceAddRequest request) {
        Resource resource = resourceDao.findById(id);
        if (!ObjectUtil.isEmpty(resource)) {
            //非叶子节点并且要修改url,需要级联修改子节点的url
            if (!resource.isLeaf() && !resource.getUrl().equals(request.getUrl())) {
                List<ResourceResponse> responses = getResourceResponses(id);
                for (ResourceResponse response : responses) {
                    String newUrl = response.getUrl().replace(resource.getUrl(), request.getUrl());
                    resourceDao.updateUrlById(newUrl, response.getId());
                }
            }
            resource.setMenuName(request.getMenuName());
            resource.setUrl(request.getUrl());
            resource.setParentId(request.getParentId());
            resource.setRemark(request.getRemark());
            resourceDao.updateById(resource);
        }
        return CommonResult.success();
    }

    /**
     * 递归获取子资源列表
     *
     * @param parentId
     * @return
     */
    private List<ResourceResponse> getResourceResponses(Long parentId) {
        List<Resource> resources = resourceDao.findByParentId(parentId);
        List<ResourceResponse> responses = resources.stream().map(resource -> {
            ResourceResponse response = ConvertUtil.convert(resource, ResourceResponse.class);
            if (!resource.isLeaf()) {
                response.setChildren(this.getResourceResponses(resource.getId()));
            } else {
                response.setChildren(Collections.emptyList());
            }
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
