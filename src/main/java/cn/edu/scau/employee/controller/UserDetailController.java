package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.request.UserDetailQueryRequest;
import cn.edu.scau.employee.common.request.UserQueryRequest;
import cn.edu.scau.employee.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/2 15:55
 */
@Api(description = "员工信息Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/info")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @ApiOperation(value = "分页查询员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody UserDetailQueryRequest request) {
        return userDetailService.findByName(request);
    }

    @ApiOperation(value = "添加员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody UserDetailAddRequest request) {
        return userDetailService.add(request);
    }

    @ApiOperation(value = "删除员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return userDetailService.deleteByIds(ids);
    }

    @ApiOperation(value = "更新员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UserDetailAddRequest request) {
        return userDetailService.updateById(id, request);
    }
}
