package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.DeptAddRequest;
import cn.edu.scau.employee.common.request.DeptQueryRequest;
import cn.edu.scau.employee.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/30 23:51
 */
@Api(description = "部门Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "分页查询所有部门信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody DeptQueryRequest request) {
        return departmentService.findByName(request);
    }

    @ApiOperation(value = "查询所有部门信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CommonResult query() {
        return departmentService.findAll();
    }

    @ApiOperation(value = "添加部门信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody DeptAddRequest request) {
        return departmentService.add(request);
    }

    @ApiOperation(value = "修改部门信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody DeptAddRequest request) {
        return departmentService.update(id, request);
    }

    @ApiOperation(value = "删除部门信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam Long[] ids) {
        return departmentService.deleteByIds(Arrays.asList(ids));
    }
}
