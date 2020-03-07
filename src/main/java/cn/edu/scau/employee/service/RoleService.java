package cn.edu.scau.employee.service;


import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.RoleAddRequest;
import cn.edu.scau.employee.common.model.request.RoleQueryRequest;

import java.util.List;

/**
 * @author chen.jiale
 * @description 角色管理业务接口
 * @date 2019/11/20 15:26
 */
public interface RoleService {
    /**
     * 查询所有角色
     *
     * @return
     */
    CommonResult findAll();

    /**
     * 添加角色
     *
     * @param request
     * @return
     */
    CommonResult add(RoleAddRequest request);

    /**
     * 更新角色
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult update(Long id, RoleAddRequest request);

    /**
     * 分页查询角色
     *
     * @param request
     * @return
     * @throws Exception
     */
    CommonResult findByName(RoleQueryRequest request) throws Exception;

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);
}
