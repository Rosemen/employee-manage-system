package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.request.UserDetailQueryRequest;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 员工信息Service接口
 * @date 2020/2/2 15:58
 */
public interface UserDetailService {
    /**
     * 分页查询用户信息
     *
     * @param request
     * @return
     */
    CommonResult findByName(UserDetailQueryRequest request);

    /**
     * 添加用户信息
     *
     * @param request
     * @return
     */
    CommonResult add(UserDetailAddRequest request);

    /**
     * 删除用户信息
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);

    /**
     * 更新用户信息
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult updateById(Long id, UserDetailAddRequest request);
}
