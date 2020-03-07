package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.RoleAddRequest;
import cn.edu.scau.employee.common.model.request.RoleQueryRequest;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/25 22:10
 */
@Api(description = "角色Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询所有角色")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CommonResult query() {
        return roleService.findAll();
    }

    @ApiOperation(value = "分页查询角色")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody RoleQueryRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return roleService.findByName(request);
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam Long[] ids) {
        return roleService.deleteByIds(Arrays.asList(ids));
    }

    @ApiOperation(value = "添加角色")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody RoleAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return roleService.add(request);
    }

    @ApiOperation(value = "更新角色")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody RoleAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return roleService.update(id, request);
    }
}
