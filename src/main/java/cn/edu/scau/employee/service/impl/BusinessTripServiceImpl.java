package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.employee.common.enums.BusinessStatusEnum;
import cn.edu.scau.employee.common.request.BusinessTripAddRequest;
import cn.edu.scau.employee.common.response.BusinessTripResponse;
import cn.edu.scau.employee.dao.BusinessTripDao;
import cn.edu.scau.employee.entity.BusinessTrip;
import cn.edu.scau.employee.service.BusinessTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/21 23:15
 */
@Service
public class BusinessTripServiceImpl implements BusinessTripService {

    @Autowired
    private BusinessTripDao businessTripDao;

    @Override
    public CommonResult businessAway(Long empNo, BusinessTripAddRequest request) {
        BusinessTrip businessTrip = ConvertUtil.convert(request, BusinessTrip.class);
        businessTrip.setEmpNo(empNo);
        businessTrip.setStatus(BusinessStatusEnum.WAIT_HANDLE.getCode());
        businessTripDao.add(businessTrip);
        return CommonResult.success();
    }

    @Override
    public CommonResult queryBusinessTrips(Long empNo, Integer status) {
        List<BusinessTrip> businessTrips = businessTripDao.selectByEmpNoAndStatus(empNo, status);
        List<BusinessTripResponse> responses = new ArrayList<>();
        businessTrips.forEach(businessTrip -> {
            BusinessTripResponse response = ConvertUtil.convert(businessTrip, BusinessTripResponse.class);
            response.setAuditorStatus(BusinessStatusEnum.get(businessTrip.getStatus()).getMsg());
            responses.add(response);
        });
        return CommonResult.success(responses);
    }
}
