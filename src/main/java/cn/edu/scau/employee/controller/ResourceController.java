package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.ResourceAddRequest;
import cn.edu.scau.employee.common.model.request.ResourceQueryRequest;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/25 22:10
 */
@Api(description = "资源Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/res")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "查询资源")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query/{parentId}", method = RequestMethod.GET)
    public CommonResult query(@PathVariable("parentId") Long parentId) {
        return resourceService.findByParentId(parentId);
    }

    @ApiOperation(value = "查询所有资源")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CommonResult query() {
        return resourceService.findAll();
    }

    @ApiOperation(value = "分页查询资源")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody ResourceQueryRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return resourceService.findByName(request);
    }

    @ApiOperation(value = "添加资源")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody ResourceAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return resourceService.add(request);
    }

    @ApiOperation(value = "编辑资源")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody ResourceAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return resourceService.update(id, request);
    }

    @ApiOperation(value = "删除资源")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam Long[] ids) {
        List<Long> idList = new ArrayList<>(ids.length);
        Collections.addAll(idList, ids);
        return resourceService.deleteByIds(idList);
    }
}
