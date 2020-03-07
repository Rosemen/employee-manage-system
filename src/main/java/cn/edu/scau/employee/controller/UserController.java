package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.request.UserAddRequest;
import cn.edu.scau.employee.common.model.request.UserLoginRequest;
import cn.edu.scau.employee.common.model.request.UserQueryRequest;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author chen.jiale
 * @description 用户Controller
 * @date 2019/11/10 16:54
 */
@Api(description = "用户Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${spring.token.name}")
    private String tokenName;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UserLoginRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return userService.login(request);
    }

    @ApiOperation(value = "用户登出")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public CommonResult logout(HttpServletRequest request) {
        String token = request.getHeader(tokenName);
        return userService.logout(token);
    }

    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody UserAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return userService.add(request);
    }

    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UserAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return userService.update(id, request);
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam(value = "ids") Long[] ids) {
        return userService.delete(Arrays.asList(ids));
    }

    @ApiOperation(value = "查询用户")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody UserQueryRequest request) throws Exception {
        return userService.findByUsername(request);
    }

    @ApiOperation(value = "根据token查询用户")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CommonResult query(HttpServletRequest request) throws Exception {
        String token = request.getHeader(tokenName);
        return userService.findByToken(token);
    }
}
