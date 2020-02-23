package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.AttendCountRequest;
import cn.edu.scau.employee.common.request.AttendanceQueryRequest;
import cn.edu.scau.employee.common.request.BusinessTripAddRequest;
import cn.edu.scau.employee.common.request.LeaveAddRequest;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.service.AttendanceService;
import cn.edu.scau.employee.service.BusinessTripService;
import cn.edu.scau.employee.service.LeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 考勤管理控制器
 * @date 2020/2/5 14:35
 */
@Api(description = "员工考勤信息Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/attend")
public class AttendController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private BusinessTripService businessTripService;

    @ApiOperation(value = "统计考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody AttendCountRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return attendanceService.count(request);
    }

    @ApiOperation(value = "统计某员工的考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query/{empNo}", method = RequestMethod.POST)
    public CommonResult query(@PathVariable("empNo") Long empNo, @RequestBody AttendCountRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return attendanceService.countByEmpNo(request, empNo);
    }

    @ApiOperation(value = "每日打卡")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/clock/{empNo}", method = RequestMethod.GET)
    public CommonResult clock(@PathVariable("empNo") Long empNo) {
        return attendanceService.clock(empNo);
    }

    @ApiOperation(value = "请假")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/leave/{empNo}", method = RequestMethod.POST)
    public CommonResult askForLeave(@PathVariable("empNo") Long empNo, @RequestBody LeaveAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return leaveService.askForLeave(empNo, request);
    }

    @ApiOperation(value = "出差申请")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/business/{empNo}", method = RequestMethod.POST)
    public CommonResult businessAway(@PathVariable("empNo") Long empNo, @RequestBody BusinessTripAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return businessTripService.businessAway(empNo, request);
    }

    @ApiOperation(value = "查询请假记录")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query/leaves", method = RequestMethod.GET)
    public CommonResult queryLeaves(@RequestParam(value = "empNo", required = false) Long empNo, @RequestParam(value = "status", required = false) Integer status) {
        return leaveService.queryLeaves(empNo, status);
    }

    @ApiOperation(value = "查询出差记录")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query/business", method = RequestMethod.GET)
    public CommonResult queryBusinessTrips(@RequestParam(value = "empNo", required = false) Long empNo, @RequestParam(value = "status", required = false) Integer status) {
        return businessTripService.queryBusinessTrips(empNo, status);
    }

    @ApiOperation(value = "查询每日打卡记录")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query/attendance/{empNo}", method = RequestMethod.POST)
    public CommonResult queryAttendance(@PathVariable("empNo") Long empNo, @RequestBody AttendanceQueryRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return attendanceService.queryAttendances(empNo, request);
    }

    @ApiOperation(value = "删除考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return attendanceService.deleteByIds(ids);
    }
}
