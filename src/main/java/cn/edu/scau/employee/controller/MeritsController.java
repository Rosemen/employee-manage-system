package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.MeritsAddRequest;
import cn.edu.scau.employee.common.model.request.MeritsQueryRequest;
import cn.edu.scau.employee.common.model.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.service.MeritsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/3/7 15:55
 */
@Api(description = "绩效Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/merits")
public class MeritsController {

    @Autowired
    private MeritsService meritsService;

    @ApiOperation(value = "分页查询员工绩效")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody MeritsQueryRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return CommonResult.success();
    }

    @ApiOperation(value = "添加绩效信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody MeritsAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除绩效信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return CommonResult.success();
    }

    @ApiOperation(value = "更新绩效信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UserDetailAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return CommonResult.success();
    }
}
