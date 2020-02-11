package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.constant.PageConstant;
import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.*;
import cn.edu.scau.employee.common.request.UserAddRequest;
import cn.edu.scau.employee.common.request.UserLoginRequest;
import cn.edu.scau.employee.common.request.UserQueryRequest;
import cn.edu.scau.employee.common.response.UserResponse;
import cn.edu.scau.employee.common.util.EncryptUtil;
import cn.edu.scau.employee.dao.TokenDao;
import cn.edu.scau.employee.dao.UserDao;
import cn.edu.scau.employee.dao.UserDetailDao;
import cn.edu.scau.employee.entity.User;
import cn.edu.scau.employee.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description 用户业务接口实现类
 * @date 2019/12/22 16:24
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private TokenDao tokenDao;

    @Override
    public CommonResult login(UserLoginRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(request.getUsername(),
                request.getPassword());
        currentUser.login(token);
        CommonResult result = CommonResult.success();
        if (currentUser.isAuthenticated()) {
            //登录成功，生成并返回token
            User user = (User) currentUser.getPrincipal();
            String tokenStr = TokenUtil.generateToken(user.getUsername());
            //保存到Redis
            tokenDao.saveUser(tokenStr, JsonUtil.objectToJson(user));
            result.setData(tokenStr);
        }
        return result;
    }

    @Override
    public CommonResult logout(String token) {
        tokenDao.clearToken(token);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return CommonResult.success();
    }

    @Override
    public CommonResult importExcel(byte[] bytes) {
        return null;
    }

    @Override
    public CommonResult exportExcel() throws Exception {
        return null;
    }

    @Override
    public CommonResult add(UserAddRequest request) {
        User user = ConvertUtil.convert(request, User.class);
        user.setPassword(EncryptUtil.getEncryptedPassword(user.getUsername(), user.getPassword()));
        user.setCreateTime(DateUtil.currentDate());
        Long id = userDao.add(user);
        return CommonResult.success(id);
    }

    @Override
    public CommonResult update(Long id, UserAddRequest request) {
        User user = ConvertUtil.convert(request, User.class);
        user.setId(id);
        user.setUpdateTime(DateUtil.currentDate());
        if (!StringUtils.isEmpty(request.getPassword())) {
            user.setPassword(EncryptUtil.getEncryptedPassword(request.getUsername(), request.getPassword()));
        }
        userDao.updateById(user);
        return CommonResult.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult delete(List<Long> ids) {
        //删除帐号对应的员工信息
        List<Long> userDetailIds = ids.stream().map(id -> {
            User user = userDao.findById(id);
            return user.getUserDetailId();
        }).collect(Collectors.toList());
        userDetailDao.deleteByIds(userDetailIds);
        //删除用户帐号
        int rows = userDao.deleteByIds(ids);
        return CommonResult.success(rows);
    }

    @Override
    public CommonResult findByUsername(UserQueryRequest request) {
        PageHelper.startPage(request.getPage().getCurrentPage(),
                request.getPage().getPageSize());
        List<User> users = null;
        if (StringUtils.isEmpty(request.getUsername())) {
            users = userDao.findAll();
        } else {
            users = userDao.findByUsername(request.getUsername());
        }
        List<UserResponse> userResponses = users.stream().map(user -> {
            UserResponse response = ConvertUtil.convert(user, UserResponse.class);
            return response;
        }).collect(Collectors.toList());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return PageCommonResult.success((int) pageInfo.getTotal(), userResponses);
    }

    @Override
    public CommonResult findByToken(String token) {
        String userInfo = tokenDao.getUserInfoByToken(token);
        User user = JsonUtil.jsonToObject(userInfo, User.class);
        UserResponse response = ConvertUtil.convert(user, UserResponse.class);
        return CommonResult.success(response);
    }
}
