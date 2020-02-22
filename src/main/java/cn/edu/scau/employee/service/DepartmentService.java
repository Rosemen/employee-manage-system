package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.DeptAddRequest;
import cn.edu.scau.employee.common.request.DeptQueryRequest;
import cn.edu.scau.employee.config.exception.EmployeeException;

import java.util.List;

/**
 * @author chen.jiale
 * @description 部门管理业务接口
 * @date 2019/12/3 8:25
 */
public interface DepartmentService {

    /**
     * 查询所有部门信息
     *
     * @return
     */
    CommonResult findAll();

    /**
     * 根据id列表删除部门
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);

    /**
     * 添加部门
     *
     * @param request
     * @return
     */
    CommonResult add(DeptAddRequest request);

    /**
     * 修改部门
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult update(Long id, DeptAddRequest request);

    /**
     * 根据部门名称查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    CommonResult findByName(DeptQueryRequest request) throws Exception;
}
