package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.LeaveAddRequest;

/**
 * @author chen.jiale
 * @Description 请假业务处理接口
 * @date 2020/2/21 22:48
 */
public interface LeaveService {
    /**
     * 申请请假
     *
     * @param empNo   员工编号
     * @param request 请假内容
     * @return
     */
    CommonResult askForLeave(String empNo, LeaveAddRequest request);

    /**
     * 查询请假情况
     *
     * @param empNo
     * @param status
     * @return
     */
    CommonResult queryLeaves(String empNo, Integer status);
}
