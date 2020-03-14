package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.constant.PageConstant;
import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.result.PageCommonResult;
import cn.edu.scau.common.util.*;
import cn.edu.scau.employee.common.model.SalaryExcelItem;
import cn.edu.scau.employee.common.model.request.SalaryAddRequest;
import cn.edu.scau.employee.common.model.request.SalaryQueryRequest;
import cn.edu.scau.employee.common.model.response.SalaryResponse;
import cn.edu.scau.employee.common.util.ExcelUtil;
import cn.edu.scau.employee.config.exception.EmployeeException;
import cn.edu.scau.employee.dao.DepartmentDao;
import cn.edu.scau.employee.dao.SalaryDao;
import cn.edu.scau.employee.dao.UserDetailDao;
import cn.edu.scau.employee.entity.Department;
import cn.edu.scau.employee.entity.Salary;
import cn.edu.scau.employee.entity.UserDetail;
import cn.edu.scau.employee.service.SalaryService;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/3/7 21:02
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    private static final Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public CommonResult query(SalaryQueryRequest request) throws Exception {
        PageConstant page = request.getPage();
        Integer currentPage = page.getCurrentPage();
        Integer pageSize = page.getPageSize();
        if (ObjectUtil.isIntegerEmpty(currentPage) || ObjectUtil.isIntegerEmpty(pageSize)) {
            logger.error("分页信息不能为空");
            throw new EmployeeException("分页信息不能为空");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Salary> salaries = salaryDao.findByEmpNo(request.getEmpNo(), request.getYear(), request.getMonth() + 1);
        List<SalaryResponse> responses = new ArrayList<>();
        salaries.forEach(salary -> {
            SalaryResponse response = ConvertUtil.convert(salary, SalaryResponse.class);
            UserDetail userDetail = userDetailDao.findByEmpNo(salary.getEmpNo());
            if (!ObjectUtil.isEmpty(userDetail)) {
                response.setName(userDetail.getName());
                Department department = departmentDao.findById(userDetail.getDeptId());
                if (!ObjectUtil.isEmpty(department)) {
                    response.setDept(department.getName());
                }
            }
            responses.add(response);
        });
        PageInfo<Salary> pageInfo = new PageInfo<>(salaries);
        return PageCommonResult.success((int) pageInfo.getTotal(), responses);
    }

    @Override
    public CommonResult add(SalaryAddRequest request) throws Exception {
        Salary salary = ConvertUtil.convert(request, Salary.class);
        Date currentDate = new Date();
        String empNo = request.getEmpNo();
        int year = DateUtil.getYear(currentDate);
        int month = DateUtil.getMonth(currentDate);
        List<Salary> salaries = salaryDao.findByEmpNo(empNo, year, month);
        if (!CollectionUtil.isEmpty(salaries)) {
            logger.error("工号: {}, {}年{}月工资已发放", empNo, year, month - 1);
            throw new EmployeeException("当月已发放工资,无须再录入工资信息");
        }
        if (request.getActualSalary() != getActualSalary(salary)) {
            logger.error("实际发放薪资不正确,salary={}", JsonUtil.objectToJson(request));
            throw new EmployeeException("实际发放薪资不正确");
        }
        salary.setPaymentTime(currentDate);
        Long id = salaryDao.insert(salary);
        return CommonResult.success(id);
    }

    @Override
    public CommonResult deleteByIds(List<Long> ids) {
        int rows = salaryDao.deleteByIds(ids);
        return CommonResult.success(rows);
    }

    @Override
    public CommonResult updateById(Long id, SalaryAddRequest request) throws Exception {
        Salary salary = ConvertUtil.convert(request, Salary.class);
        if (request.getActualSalary() != getActualSalary(salary)) {
            logger.error("实发工资不正确, salary={}", JsonUtil.objectToJson(request));
            throw new EmployeeException("实发薪资不正确");
        }
        salary.setId(id);
        salary.setUpdateTime(new Date());
        salaryDao.updateById(salary);
        return CommonResult.success();
    }

    @Override
    public CommonResult upload(byte[] bytes) throws Exception {
        try {
            EasyExcel.read(new ByteArrayInputStream(bytes), SalaryExcelItem.class,
                    ExcelUtil.getListener(batchInsert())).sheet().doRead();
        } catch (Exception ex) {
            logger.error("上传员工薪资文件错误", ex);
            throw new EmployeeException("上传员工薪资文件错误");
        }
        return CommonResult.success();
    }

    @Override
    public List<SalaryExcelItem> download() throws Exception {
        List<Salary> salaries = salaryDao.findByEmpNo(null, null, null);
        if (CollectionUtil.isEmpty(salaries)) {
            logger.error("员工薪资信息不存在");
            throw new EmployeeException("员工薪资信息不存在");
        }
        List<SalaryExcelItem> salaryExcelItems = new ArrayList<>();
        salaries.forEach(salary -> {
            SalaryExcelItem salaryExcelItem = ConvertUtil.convert(salary, SalaryExcelItem.class);
            UserDetail userDetail = userDetailDao.findByEmpNo(salary.getEmpNo());
            if (!ObjectUtil.isEmpty(userDetail)) {
                salaryExcelItem.setName(userDetail.getName());
                Department department = departmentDao.findById(userDetail.getDeptId());
                if (!ObjectUtil.isEmpty(department)) {
                    salaryExcelItem.setDept(department.getName());
                }
            }
            salaryExcelItems.add(salaryExcelItem);
        });
        return salaryExcelItems;
    }

    /**
     * 计算实际发放工资
     *
     * @param salary
     * @return
     */
    private double getActualSalary(Salary salary) {
        double actualSalary = salary.getBaseSalary() + salary.getMealAllowance() + salary.getOvertimeAllowance()
                + salary.getOtherAllowance() + salary.getHousingSubsidies() - salary.getFromAbsences() - salary.getHousingProvidentFund();
        return actualSalary;
    }

    private Consumer<List<SalaryExcelItem>> batchInsert() {
        return userDetailExcelRequests -> {
            try {
                for (SalaryExcelItem item : userDetailExcelRequests) {
                    Salary salary = ConvertUtil.convert(item, Salary.class);
                    List<Salary> salaries = salaryDao.findByEmpNo(salary.getEmpNo(), DateUtil.getYear(salary.getPaymentTime()),
                            DateUtil.getMonth(salary.getPaymentTime()));
                    if (!CollectionUtil.isEmpty(salaries)) {
                        logger.error("导入员工薪资信息异常,已存在相应记录, salary={}", JsonUtil.objectToJson(salary));
                        throw new EmployeeException("导入员工薪资信息异常");
                    }
                    salaryDao.insert(salary);
                }
            } catch (Exception ex) {
                logger.error("添加用户信息异常", ex);
                throw new RuntimeException(ex.getMessage());
            }
        };
    }
}
