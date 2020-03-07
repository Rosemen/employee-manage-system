package cn.edu.scau.employee.service;


import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.UserAddRequest;
import cn.edu.scau.employee.common.model.request.UserLoginRequest;
import cn.edu.scau.employee.common.model.request.UserQueryRequest;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author chen.jiale
 * @date 2019/11/10 16:55
 */
public interface UserService {
    /**
     * 登录
     *
     * @param request
     * @return
     */
    CommonResult login(UserLoginRequest request);

    /**
     * 登出
     *
     * @param token
     * @return
     */
    CommonResult logout(String token);

    /**
     * 添加用户
     *
     * @param request
     * @return
     */
    CommonResult add(UserAddRequest request);

    /**
     * 修改用户
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult update(Long id, UserAddRequest request);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    CommonResult delete(List<Long> ids);

    /**
     * 根据用户名查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    CommonResult findByUsername(UserQueryRequest request) throws Exception;

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    CommonResult findByToken(String token) throws Exception;
}
