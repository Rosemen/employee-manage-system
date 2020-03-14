package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.constant.PageConstant;
import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.CollectionUtil;
import cn.edu.scau.common.util.ConvertUtil;
import cn.edu.scau.common.util.DateUtil;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.common.constant.EmpInfoConstant;
import cn.edu.scau.employee.common.model.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.model.UserDetailExcelItem;
import cn.edu.scau.employee.common.model.request.UserDetailQueryRequest;
import cn.edu.scau.employee.common.model.response.UserDetailResponse;
import cn.edu.scau.employee.common.util.ExcelUtil;
import cn.edu.scau.employee.config.excel.TableStyleStrategy;
import cn.edu.scau.employee.config.exception.EmployeeException;
import cn.edu.scau.employee.dao.DepartmentDao;
import cn.edu.scau.employee.dao.UserDetailDao;
import cn.edu.scau.employee.entity.Department;
import cn.edu.scau.employee.entity.UserDetail;
import cn.edu.scau.employee.service.UserDetailService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @Description 员工信息Service接口实现类
 * @date 2020/2/2 15:59
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public CommonResult findByName(UserDetailQueryRequest request) throws Exception {
        PageConstant page = request.getPage();
        Integer currentPage = page.getCurrentPage();
        Integer pageSize = page.getPageSize();
        if (ObjectUtil.isIntegerEmpty(currentPage) || ObjectUtil.isIntegerEmpty(pageSize)) {
            logger.error("分页信息不能为空");
            throw new EmployeeException("分页信息不能为空");
        }
        PageHelper.startPage(currentPage,
                pageSize);
        List<UserDetail> userDetails = userDetailDao.findByName(request.getName());
        List<UserDetailResponse> responses = userDetails.stream().map(userDetail -> {
            UserDetailResponse response = ConvertUtil.convert(userDetail, UserDetailResponse.class);
            return response;
        }).collect(Collectors.toList());
        PageInfo<UserDetail> pageInfo = new PageInfo<>(userDetails);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }

    @Override
    public CommonResult add(UserDetailAddRequest request) {
        UserDetail userDetail = ConvertUtil.convert(request, UserDetail.class);
        String empNo = generateEmpNo() + "";
        userDetail.setEmpNo(empNo);
        Long id = userDetailDao.add(userDetail);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("empNo", empNo);
        resultMap.put("id", id);
        return CommonResult.success(resultMap);
    }

    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        int rows = userDetailDao.deleteByIds(ids);
        return CommonResult.success(rows);
    }

    @Override
    public CommonResult updateById(Long id, UserDetailAddRequest request) {
        UserDetail userDetail = ConvertUtil.convert(request, UserDetail.class);
        userDetail.setId(id);
        userDetailDao.updateById(userDetail);
        return CommonResult.success();
    }

    @Override
    public CommonResult upload(byte[] bytes) throws Exception {
        try {
            EasyExcel.read(new ByteArrayInputStream(bytes), UserDetailExcelItem.class,
                    ExcelUtil.getListener(batchInsert())).sheet().doRead();
        } catch (Exception ex) {
            logger.error("上传用户信息文件错误", ex);
            throw new EmployeeException("上传用户信息文件错误");
        }
        return CommonResult.success();
    }

    @Override
    public List<UserDetailExcelItem> download() throws Exception {
        List<UserDetail> userDetails = userDetailDao.findAll();
        List<UserDetailExcelItem> userDetailExcelItems = new ArrayList<>();
        userDetails.forEach(userDetail -> {
            UserDetailExcelItem item = ConvertUtil.convert(userDetail, UserDetailExcelItem.class);
            Department department = departmentDao.findById(userDetail.getDeptId());
            String deptName = !ObjectUtil.isEmpty(department) ? department.getName() : "";
            item.setDept(deptName);
            userDetailExcelItems.add(item);
        });
        return userDetailExcelItems;
    }

    @Override
    public CommonResult findByEmpNo(String empNo) {
        UserDetail userDetail = userDetailDao.findByEmpNo(empNo);
        UserDetailResponse response = null;
        if (!ObjectUtil.isEmpty(userDetail)) {
            response = ConvertUtil.convert(userDetail, UserDetailResponse.class);
        }
        return CommonResult.success(response);
    }

    private Long generateEmpNo() {
        RedisAtomicLong counter = new RedisAtomicLong(EmpInfoConstant.EMP_INDEX, redisTemplate.getConnectionFactory());
        long number = counter.incrementAndGet();
        number += EmpInfoConstant.MAX_NUMBER;
        String prefix = DateUtil.dateToStr(new Date(), "yyyyMMdd");
        String suffix = (number + "").substring(1);
        String empNo = prefix + suffix;
        return Long.valueOf(empNo);
    }

    private Consumer<List<UserDetailExcelItem>> batchInsert() {
        return userDetailExcelRequests -> {
            try {
                for (UserDetailExcelItem request : userDetailExcelRequests) {
                    UserDetail userDetail = ConvertUtil.convert(request, UserDetail.class);
                    List<Department> departments = departmentDao.findByName(request.getDept());
                    if (!CollectionUtil.isEmpty(departments)) {
                        Department department = departments.get(0);
                        userDetail.setDeptId(department.getId());
                    }
                    userDetailDao.add(userDetail);
                }
            } catch (Exception ex) {
                logger.error("添加用户信息异常", ex);
                throw ex;
            }
        };
    }

}
