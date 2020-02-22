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
     * @throws Exception
     */
    CommonResult findByName(UserDetailQueryRequest request) throws Exception;

    /**
     * 添加用户信息
     *
     * @param request
     * @throws Exception
     * @return
     */
    CommonResult add(UserDetailAddRequest request) throws Exception;

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
     * @throws Exception
     */
    CommonResult updateById(Long id, UserDetailAddRequest request) throws Exception;
}
