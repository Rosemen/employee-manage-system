package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.constant.PageConstant;
import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.CollectionUtil;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.common.constant.EmpInfoConstant;
import cn.edu.scau.employee.common.model.request.AttendCountRequest;
import cn.edu.scau.employee.common.model.request.AttendanceQueryRequest;
import cn.edu.scau.employee.common.model.response.AttendCountResponse;
import cn.edu.scau.employee.common.model.response.AttendanceResponse;
import cn.edu.scau.employee.config.exception.EmployeeException;
import cn.edu.scau.employee.dao.*;
import cn.edu.scau.employee.entity.*;
import cn.edu.scau.employee.service.AttendanceService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/5 14:37
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private LeavesDao leavesDao;

    @Autowired
    private BusinessTripDao businessTripDao;

    @Override
    public CommonResult countByEmpNo(AttendCountRequest request, Long empNo) {
        UserDetail userDetail = userDetailDao.findByEmpNo(empNo);
        AttendCountResponse response = this.doCountAttendRecord(userDetail, request);
        return CommonResult.success(response);
    }

    @Override
    public CommonResult count(AttendCountRequest request) {
        List<AttendCountResponse> responses = new ArrayList<>();
        List<UserDetail> userDetails = userDetailDao.findAll();
        if (!CollectionUtil.isEmpty(userDetails)) {
            for (UserDetail userDetail : userDetails) {
                AttendCountResponse response = this.doCountAttendRecord(userDetail, request);
                if (!ObjectUtil.isEmpty(response)) {
                    responses.add(response);
                }
            }
        }
        return CommonResult.success(responses);
    }

    /**
     * 统计考勤
     *
     * @param userDetail
     * @param request
     * @return
     */
    private AttendCountResponse doCountAttendRecord(UserDetail userDetail, AttendCountRequest request) {
        Long empNo = userDetail.getEmpNo();
        AttendCountResponse response = new AttendCountResponse();
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
        //没有对应的记录
        if (CollectionUtil.isEmpty(attendances) && CollectionUtil.isEmpty(leaves) && CollectionUtil.isEmpty(businessTrips)) {
            return null;
        }
        //统计实际出勤天数,迟到天数,早退天数
        Integer actualAttendDays = 0, lateDays = 0, earlyDays = 0;
        if (!CollectionUtil.isEmpty(attendances)) {
            Map<String, Integer> attendMap = countAttendDays(attendances);
            actualAttendDays = attendMap.get("actualAttendDays");
            earlyDays = attendMap.get("earlyDays");
            lateDays = attendMap.get("lateDays");
        }
        //统计请假天数
        Integer leaveDays = 0;
        if (!CollectionUtil.isEmpty(leaves)) {
            leaveDays = countLeaveDays(leaves, request);
        }
        //统计出差天数
        Integer businessDays = 0;
        if (!CollectionUtil.isEmpty(businessTrips)) {
            businessDays = countBusinessDays(businessTrips, request);
        }
        //旷工天数
        Integer skiDays = this.countSkiDays(request, attendDays, actualAttendDays, leaveDays, businessDays);
        response.setEmpNo(empNo);
        response.setName(userDetail.getName());
        Department department = departmentDao.findById(userDetail.getDeptId());
        response.setDept(department.getName());
        response.setAttendDays(attendDays);
        response.setActualAttendDays(actualAttendDays);
        response.setLateDays(earlyDays);
        response.setEarlyDays(lateDays);
        response.setLeaveDays(leaveDays);
        response.setBusinessDays(businessDays);
        response.setSkiDays(skiDays);
        return response;
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
    private Integer countLeaveDays(List<Leaves> leaves, AttendCountRequest request) {
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
    private Integer countBusinessDays(List<BusinessTrip> businessTrips, AttendCountRequest request) {
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

    /**
     * 统计缺勤天数
     *
     * @return
     */
    private Integer countSkiDays(AttendCountRequest request, Integer attendDays, Integer actualAttendDays,
                                 Integer leaveDays, Integer businessDays) {
        Integer skiDays = 0;
        Date currentDate = DateUtil.strToDate(DateUtil.currentDateStr(), "yyyy-MM-dd");
        int currentYear = DateUtil.getYear(currentDate);
        int currentMonth = DateUtil.getMonth(currentDate);
        int currentQuarter = DateUtil.getQuarter(currentDate);
        Integer range = request.getRange();
        Integer year = request.getYear();
        Integer month = request.getMonth();
        Integer quarter = request.getQuarter();
        if (EmpInfoConstant.MONTH_RANGE == range) {
            //获取本月未出席天数
            if (currentYear < year) {
                skiDays = 0;
            } else if (currentMonth < month) {
                skiDays = 0;
            } else if (currentMonth > month) {
                skiDays = attendDays - actualAttendDays - leaveDays - businessDays;
            } else {
                Date lastDate = DateUtil.getLastDayOfMonth(year, month);
                Integer extraDays = DateUtil.getWorkDaysOfTwoDate(currentDate, lastDate);
                skiDays = attendDays - actualAttendDays - leaveDays + businessDays - extraDays;
            }
        } else if (EmpInfoConstant.QUARTER_RANGE == range) {
            //获取本季度未出席天数
            if (currentYear < year) {
                skiDays = 0;
            } else if (currentQuarter < quarter) {
                skiDays = 0;
            } else if (currentQuarter > quarter) {
                skiDays = attendDays - actualAttendDays - leaveDays - businessDays;
            } else {
                Date lastDate = DateUtil.getLastDayOfQuarter(year, quarter);
                Integer extraDays = DateUtil.getWorkDaysOfTwoDate(currentDate, lastDate);
                skiDays = attendDays - actualAttendDays - leaveDays + businessDays - extraDays;
            }

        }
        return skiDays;
    }

    @Override
    public CommonResult clock(Long empNo) {
        Date currentDate = new Date();
        Attendance attendance = attendanceDao.findByEmpNoAndStartTime(empNo,
                DateUtil.dateToStr(currentDate, "yyyy-MM-dd"));
        if (ObjectUtil.isEmpty(attendance)) {
            attendance = new Attendance();
            attendance.setEmpNo(empNo);
            attendance.setStartTime(currentDate);
            String startTimeStr = DateUtil.dateToStr(currentDate, "HH:mm:ss");
            if (startTimeStr.compareTo(EmpInfoConstant.START_TIME) > 1) {
                attendance.setLate(true);
            } else {
                attendance.setLate(false);
            }
            attendanceDao.add(attendance);
        } else {
            attendance.setEndTime(new Date());
            attendanceDao.updateById(attendance);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult queryAttendances(Long empNo, AttendanceQueryRequest request) throws Exception {
        PageConstant page = request.getPage();
        Integer currentPage = page.getCurrentPage();
        Integer pageSize = page.getPageSize();
        if (ObjectUtil.isIntegerEmpty(currentPage) || ObjectUtil.isIntegerEmpty(pageSize)) {
            logger.error("分页信息不能为空");
            throw new EmployeeException("分页信息不能为空");
        }
        List<Attendance> attendances = attendanceDao.findByEmpNoAndMonth(request.getYear(), request.getMonth(), empNo);
        List<AttendanceResponse> responses = new ArrayList<>();
        attendances.forEach(attendance -> {
            AttendanceResponse response = ConvertUtil.convert(attendance, AttendanceResponse.class);
            responses.add(response);
        });
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }

    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        return null;
    }
}
