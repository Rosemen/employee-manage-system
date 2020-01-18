package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.employee.common.response.DepartmentResponse;
import cn.edu.scau.employee.dao.DepartmentDao;
import cn.edu.scau.employee.entity.Department;
import cn.edu.scau.employee.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CommonResult findAll() {
        List<Department> departments = departmentDao.findAll();
        List<DepartmentResponse> responses = departments.stream().map(department ->
           ConvertUtil.convert(department, DepartmentResponse.class)
        ).collect(Collectors.toList());
        return CommonResult.success(responses);
    }
}
