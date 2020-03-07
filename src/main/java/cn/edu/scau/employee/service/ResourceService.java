package cn.edu.scau.employee.service;


import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.ResourceAddRequest;
import cn.edu.scau.employee.common.model.request.ResourceQueryRequest;
import cn.edu.scau.employee.entity.Resource;

import java.util.List;


/**
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
    List<Resource> findByRoleId(Long roleId);

    /**
     * 根据父节点id查询所有子节点菜单
     *
     * @param parentId
     * @return
     */
    CommonResult findByParentId(Long parentId);

    /**
     * 根据资源名模糊查询
     *
     * @param request
     * @return
     */
    CommonResult findByName(ResourceQueryRequest request) throws Exception;

    /**
     * 添加资源
     *
     * @param request
     * @return
     */
    CommonResult add(ResourceAddRequest request);

    /**
     * 删除资源
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);

    /**
     * 查询所有资源
     *
     * @return
     */
    CommonResult findAll();

    /**
     * 更新资源
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult update(Long id, ResourceAddRequest request);
}
