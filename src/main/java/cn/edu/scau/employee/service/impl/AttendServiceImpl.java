package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.employee.common.constant.EmpInfoConstant;
import cn.edu.scau.employee.common.request.AttendAddRequest;
import cn.edu.scau.employee.common.request.AttendQueryRequest;
import cn.edu.scau.employee.common.response.AttendResponse;
import cn.edu.scau.employee.dao.AttendanceDao;
import cn.edu.scau.employee.dao.BusinessTripDao;
import cn.edu.scau.employee.dao.LeavesDao;
import cn.edu.scau.employee.dao.UserDetailDao;
import cn.edu.scau.employee.entity.Attendance;
import cn.edu.scau.employee.entity.BusinessTrip;
import cn.edu.scau.employee.entity.Leaves;
import cn.edu.scau.employee.service.AttendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/5 14:37
 */
@Service
public class AttendServiceImpl implements AttendService {

    private static final Logger logger = LoggerFactory.getLogger(AttendServiceImpl.class);

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private LeavesDao leavesDao;

    @Autowired
    private BusinessTripDao businessTripDao;

    @Override
    public CommonResult findByEmpNo(Long empNo) {
        return null;
    }

    @Override
    public CommonResult find(AttendQueryRequest request) {
        List<Long> empNos = userDetailDao.findAllEmpNos();
        List<AttendResponse> responses = empNos.stream().map(empNo -> {
            AttendResponse response = new AttendResponse();
            List<Attendance> attendances = null;
            List<Leaves> leaves = null;
            List<BusinessTrip> businessTrips = null;
            Integer year = request.getYear();
            Integer attendDays = 0;
            //按月查询
            if (EmpInfoConstant.MONTH_RANGE == request.getRange()) {
                Integer month = request.getMonth();
                attendances = attendanceDao.findByEmpNoAndMonth(year, month, empNo);
                attendDays = DateUtil.getWorkDaysOfMonth(year, month);
                leaves = leavesDao.findByEmpNoAndMonth(year, month, empNo);
                businessTrips = businessTripDao.findByEmpNoAndMonth(year, month, empNo);
            } else if (EmpInfoConstant.QUARTER_RANGE == request.getRange()) {
                //按季度查询
                Integer quarter = request.getQuarter();
                attendances = attendanceDao.findByEmpNoAndQuarter(year, quarter, empNo);
                attendDays = DateUtil.getWorkDaysOfQuarter(year, quarter);
                leaves = leavesDao.findByEmpNoAndQuarter(year, quarter, empNo);
                businessTrips = businessTripDao.findByEmpNoAndQuarter(year, quarter, empNo);
            }
            //统计实际出勤天数,迟到天数,早退天数
            Integer actualAttendDays = 0, lateDays = 0, earlyDays = 0;
            if (null != attendances && attendances.size() > 0) {
                Map<String, Integer> attendMap = countAttendDays(attendances);
                actualAttendDays = attendMap.get("actualAttendDays");
                earlyDays = attendMap.get("earlyDays");
                lateDays = attendMap.get("lateDays");
            }
            //统计请假天数
            Integer leaveDays = 0;
            if (null != leaves && leaves.size() > 0) {
                leaveDays = countLeaveDays(leaves, request);
            }
            //统计出差天数
            Integer businessDays = 0;
            if (null != businessTrips && businessTrips.size() > 0) {
                businessDays = countBusinessDays(businessTrips, request);
            }
            response.setEmpNo(empNo);
            response.setAttendDays(attendDays);
            response.setRealAttendDays(actualAttendDays);
            response.setLateDays(earlyDays);
            response.setEarlyDays(lateDays);
            response.setLeaveDays(leaveDays);
            response.setBusinessDays(businessDays);
            //旷工天数
            response.setSkiDays(attendDays - actualAttendDays - leaveDays - businessDays);
            return response;
        }).collect(Collectors.toList());
        return CommonResult.success(responses);
    }

    /**
     * 统计每日考勤结果
     *
     * @param attendances
     * @return
     */
    private Map<String, Integer> countAttendDays(List<Attendance> attendances) {
        Integer lateDays, earlyDays, actualAttendDays;
        lateDays = earlyDays = actualAttendDays = 0;
        //统计实际出勤天数
        for (Attendance attendance : attendances) {
            actualAttendDays++;
            if (attendance.isLate()) {
                lateDays++;
                continue;
            } else if (attendance.isLeaveEarly()) {
                earlyDays++;
            }
        }
        Map<String, Integer> map = new HashMap<>(3);
        map.put("actualAttendDays", actualAttendDays);
        map.put("earlyDays", earlyDays);
        map.put("lateDays", lateDays);
        return map;
    }

    /**
     * 统计某月请假天数或某季度请假天数
     *
     * @param leaves
     * @param request
     * @return
     */
    private Integer countLeaveDays(List<Leaves> leaves, AttendQueryRequest request) {
        Integer leaveDays = 0;
        //统计月份请假数
        if (EmpInfoConstant.MONTH_RANGE == request.getRange()) {
            for (Leaves leave : leaves) {
                //请假时间跨越月份,不跨月份
                if ((DateUtil.getMonth(leave.getStartTime()) == request.getMonth())
                        && (DateUtil.getMonth(leave.getEndTime()) != request.getMonth())) {
                    leaveDays += (int) DateUtil.getDaysOfTwoDate(leave.getStartTime(),
                            DateUtil.getLastDayOfMonth(request.getYear(), request.getMonth()));
                } else if ((DateUtil.getMonth(leave.getStartTime()) != request.getMonth())
                        && (DateUtil.getMonth(leave.getEndTime()) == request.getMonth())) {
                    leaveDays += (int) DateUtil.getDaysOfTwoDate(DateUtil.getFirstDayOfMonth(request.getYear(), request.getMonth()),
                            leave.getEndTime());
                } else {
                    leaveDays += (int) DateUtil.getDaysOfTwoDate(leave.getStartTime(), leave.getEndTime());
                }
                leaveDays++;
            }
        } else if (EmpInfoConstant.QUARTER_RANGE == request.getRange()) {
            for (Leaves leave : leaves) {
                //请假时间跨越季度,不跨季度
                if ((DateUtil.getQuarter(leave.getStartTime()) == request.getQuarter())
                        && (DateUtil.getQuarter(leave.getEndTime()) != request.getQuarter())) {
                    leaveDays += (int) DateUtil.getDaysOfTwoDate(leave.getStartTime(),
                            DateUtil.getLastDayOfQuarter(request.getYear(), request.getQuarter()));
                } else if ((DateUtil.getQuarter(leave.getStartTime()) != request.getQuarter())
                        && (DateUtil.getQuarter(leave.getEndTime()) == request.getQuarter())) {
                    leaveDays += (int) DateUtil.getDaysOfTwoDate(DateUtil.getFirstDayOfQuarter(request.getYear(), request.getQuarter()),
                            leave.getEndTime());
                } else {
                    leaveDays += (int) DateUtil.getDaysOfTwoDate(leave.getStartTime(), leave.getEndTime());
                }
                leaveDays++;
            }
        }
        return leaveDays;
    }

    /**
     * 统计某月出差天数或某季度出差天数
     *
     * @return
     */
    private Integer countBusinessDays(List<BusinessTrip> businessTrips, AttendQueryRequest request) {
        Integer businessDays = 0;
        //统计月份请假数
        if (EmpInfoConstant.MONTH_RANGE == request.getRange()) {
            for (BusinessTrip businessTrip : businessTrips) {
                //出差时间跨越月份,不跨月份
                if ((DateUtil.getMonth(businessTrip.getStartTime()) == request.getMonth())
                        && (DateUtil.getMonth(businessTrip.getEndTime()) != request.getMonth())) {
                    businessDays += (int) DateUtil.getDaysOfTwoDate(businessTrip.getStartTime(),
                            DateUtil.getLastDayOfMonth(request.getYear(), request.getMonth()));
                } else if ((DateUtil.getMonth(businessTrip.getStartTime()) != request.getMonth())
                        && (DateUtil.getMonth(businessTrip.getEndTime()) == request.getMonth())) {
                    businessDays += (int) DateUtil.getDaysOfTwoDate(DateUtil.getFirstDayOfMonth(request.getYear(), request.getMonth()),
                            businessTrip.getEndTime());
                } else {
                    businessDays += (int) DateUtil.getDaysOfTwoDate(businessTrip.getStartTime(), businessTrip.getEndTime());
                }
                businessDays++;
            }
        } else if (EmpInfoConstant.QUARTER_RANGE == request.getRange()) {
            for (BusinessTrip businessTrip : businessTrips) {
                //出差时间跨越季度,不跨季度
                if ((DateUtil.getQuarter(businessTrip.getStartTime()) == request.getQuarter())
                        && (DateUtil.getQuarter(businessTrip.getEndTime()) != request.getQuarter())) {
                    businessDays += (int) DateUtil.getDaysOfTwoDate(businessTrip.getStartTime(),
                            DateUtil.getLastDayOfQuarter(request.getYear(), request.getQuarter()));
                } else if ((DateUtil.getQuarter(businessTrip.getStartTime()) != request.getQuarter())
                        && (DateUtil.getQuarter(businessTrip.getEndTime()) == request.getQuarter())) {
                    businessDays += (int) DateUtil.getDaysOfTwoDate(DateUtil.getFirstDayOfQuarter(request.getYear(), request.getQuarter()),
                            businessTrip.getEndTime());
                } else {
                    businessDays += (int) DateUtil.getDaysOfTwoDate(businessTrip.getStartTime(), businessTrip.getEndTime());
                }
                businessDays++;
            }
        }
        return businessDays;
    }

    @Override
    public CommonResult add(AttendAddRequest request) {
        return null;
    }

    @Override
    public CommonResult updateById(Long id, AttendAddRequest request) {
        return null;
    }

    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        return null;
    }
}
