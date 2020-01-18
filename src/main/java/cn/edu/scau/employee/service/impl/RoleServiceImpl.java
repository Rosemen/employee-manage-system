package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.employee.common.request.RoleAddRequest;
import cn.edu.scau.employee.common.response.RoleResponse;
import cn.edu.scau.employee.dao.RoleDao;
import cn.edu.scau.employee.entity.Role;
import cn.edu.scau.employee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description 角色管理接口实现类
 * @date 2019/12/22 16:25
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public CommonResult findAll() {
        List<Role> roles = roleDao.findAll();
        List<RoleResponse> roleResponses = roles.stream().map(role -> {
            RoleResponse roleResponse = ConvertUtil.convert(role, RoleResponse.class);
            return roleResponse;
        }).collect(Collectors.toList());
        return CommonResult.success(roleResponses);
    }

    @Override
    public CommonResult add(RoleAddRequest request) {
        return null;
    }

    @Override
    public CommonResult delete(List<Integer> ids) {
        return null;
    }

    @Override
    public CommonResult update(Integer id, RoleAddRequest request) {
        return null;
    }
}
