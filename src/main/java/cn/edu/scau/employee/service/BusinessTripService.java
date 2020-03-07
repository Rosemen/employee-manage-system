package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.BusinessTripAddRequest;

/**
 * @author chen.jiale
 * @Description 出差业务接口
 * @date 2020/2/21 22:50
 */
public interface BusinessTripService {
    /**
     * 申请出差
     *
     * @param empNo
     * @param request
     * @return
     */
    CommonResult businessAway(Long empNo, BusinessTripAddRequest request);

    /**
     * 查询出差记录
     *
     * @param empNo
     * @param status
     * @return
     */
    CommonResult queryBusinessTrips(Long empNo, Integer status);
}
