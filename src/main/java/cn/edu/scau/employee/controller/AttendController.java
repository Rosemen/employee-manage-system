package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.AttendAddRequest;
import cn.edu.scau.employee.common.request.AttendQueryRequest;
import cn.edu.scau.employee.service.AttendService;
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
    private AttendService attendService;

    @ApiOperation(value = "分页查询考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody AttendQueryRequest request) {
        return attendService.find(request);
    }

    @ApiOperation(value = "根据工号查询考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query/{empNo}", method = RequestMethod.GET)
    public CommonResult query(@PathVariable("empNo") Long empNo) {
        return attendService.findByEmpNo(empNo);
    }

    @ApiOperation(value = "添加考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody AttendAddRequest request) {
        return attendService.add(request);
    }

    @ApiOperation(value = "修改考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody AttendAddRequest request) {
        return attendService.updateById(id, request);
    }

    @ApiOperation(value = "删除考勤信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return attendService.deleteByIds(ids);
    }
}
