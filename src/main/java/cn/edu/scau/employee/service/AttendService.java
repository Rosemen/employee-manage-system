package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.AttendAddRequest;
import cn.edu.scau.employee.common.request.AttendQueryRequest;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 考勤管理业务
 * @date 2020/2/5 14:36
 */
public interface AttendService {

    /**
     * 根据工号查询
     *
     * @param empNo
     * @return
     */
    CommonResult findByEmpNo(Long empNo);

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    CommonResult find(AttendQueryRequest request);

    /**
     * 添加考勤记录
     *
     * @param request
     * @return
     */
    CommonResult add(AttendAddRequest request);

    /**
     * 修改考勤记录
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult updateById(Long id, AttendAddRequest request);

    /**
     * 删除考勤记录
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);
}
