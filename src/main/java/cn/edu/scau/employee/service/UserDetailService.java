package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.UserDetailExcelItem;
import cn.edu.scau.employee.common.model.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.model.request.UserDetailQueryRequest;

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
     * @return
     * @throws Exception
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

    /**
     * 上传文件
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    CommonResult upload(byte[] bytes) throws Exception;

    /**
     * 导出文件
     *
     * @return
     * @throws Exception
     */
    List<UserDetailExcelItem> download() throws Exception;

    /**
     * 根据工号查询
     *
     * @param empNo
     * @return
     */
    CommonResult findByEmpNo(String empNo);

}
