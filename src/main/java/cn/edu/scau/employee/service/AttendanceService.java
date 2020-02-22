package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.AttendQueryRequest;
import cn.edu.scau.employee.common.request.BusinessTripAddRequest;
import cn.edu.scau.employee.common.request.LeaveAddRequest;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 考勤管理业务
 * @date 2020/2/5 14:36
 */
public interface AttendanceService {

    /**
     * 统计某个员工考勤情况
     *
     * @param request
     * @param empNo
     * @return
     */
    CommonResult countByEmpNo(AttendQueryRequest request, Long empNo);

    /**
     * 统计考勤情况
     *
     * @param request
     * @return
     */
    CommonResult count(AttendQueryRequest request);

    /**
     * 删除考勤记录
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);

    /**
     * 员工打卡
     *
     * @param empNo
     * @return
     */
    CommonResult clock(Long empNo);
}
