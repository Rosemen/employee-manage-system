package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.employee.common.request.DeptAddRequest;
import cn.edu.scau.employee.common.request.DeptQueryRequest;
import cn.edu.scau.employee.common.response.DepartmentResponse;
import cn.edu.scau.employee.dao.DepartmentDao;
import cn.edu.scau.employee.dao.UserDetailDao;
import cn.edu.scau.employee.entity.Department;
import cn.edu.scau.employee.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description 部门管理业务接口实现类
 * @date 2019/12/22 16:28
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    public CommonResult findAll() {
        logger.info("DepartmentServiceImpl...  查询所有部门");
        List<Department> departments = departmentDao.findAll();
        List<DepartmentResponse> responses = departments.stream().map(department ->
                ConvertUtil.convert(department, DepartmentResponse.class)
        ).collect(Collectors.toList());
        return CommonResult.success(responses);
    }

    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        logger.info("DepartmentServiceImpl...  删除部门, 部门id列表: {}",
                Arrays.toString(ids.toArray()));
        int rows = departmentDao.deleteByIds(ids);
        //更新用户的部门信息
        userDetailDao.updateByDeptIds(ids);
        return CommonResult.success(rows);
    }

    @Override
    public CommonResult add(DeptAddRequest request) {
        logger.info("DepartmentServiceImpl...  添加部门, 部门信息: {}", request.toString());
        Department department = ConvertUtil.convert(request, Department.class);
        department.setCreateTime(DateUtil.currentDate());
        department.setUpdateTime(DateUtil.currentDate());
        Long deptId = departmentDao.add(department);
        return CommonResult.success(deptId);
    }

    @Override
    public CommonResult update(Long id, DeptAddRequest request) {
        logger.info("DepartmentServiceImpl...  更新部门, 部门id: {}, 部门信息: {}", id, request.toString());
        Department department = ConvertUtil.convert(request, Department.class);
        department.setId(id);
        department.setUpdateTime(DateUtil.currentDate());
        departmentDao.updateById(department);
        return CommonResult.success();
    }

    @Override
    public CommonResult findByName(DeptQueryRequest request) {
        logger.info("DepartmentServiceImpl...  分页查询部门, 查询条件:{}", request.toString());
        PageHelper.startPage(request.getPage().getCurrentPage(),
                request.getPage().getPageSize());
        List<Department> departments = departmentDao.findByName(request.getName());
        List<DepartmentResponse> responses = departments.stream().map(department -> {
            return ConvertUtil.convert(department, DepartmentResponse.class);
        }).collect(Collectors.toList());
        PageInfo<Department> pageInfo = new PageInfo<>(departments);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }
}
