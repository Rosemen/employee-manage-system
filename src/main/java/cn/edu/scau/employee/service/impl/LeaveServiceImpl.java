package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.employee.common.enums.LeaveStatusEnum;
import cn.edu.scau.employee.common.enums.LeaveTypeEnum;
import cn.edu.scau.employee.common.model.request.LeaveAddRequest;
import cn.edu.scau.employee.common.model.response.LeaveResponse;
import cn.edu.scau.employee.dao.LeavesDao;
import cn.edu.scau.employee.entity.Leaves;
import cn.edu.scau.employee.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/21 23:13
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeavesDao leavesDao;

    @Override
    public CommonResult askForLeave(Long empNo, LeaveAddRequest request) {
        Leaves leave = ConvertUtil.convert(request, Leaves.class);
        leave.setEmpNo(empNo);
        leave.setStatus(LeaveStatusEnum.WAIT_HANDLE.getCode());
        leavesDao.add(leave);
        return CommonResult.success();
    }

    @Override
    public CommonResult queryLeaves(Long empNo, Integer status) {
        List<Leaves> leaves = leavesDao.findByEmpNoAndStatus(empNo, status);
        List<LeaveResponse> responses = new ArrayList<>();
        leaves.forEach(leave -> {
            LeaveResponse response = ConvertUtil.convert(leave, LeaveResponse.class);
            response.setAuditorStatus(LeaveStatusEnum.get(leave.getStatus()).getMsg());
            response.setLeaveType(LeaveTypeEnum.get(leave.getType()).getMsg());
            responses.add(response);
        });
        return CommonResult.success(responses);
    }
}
