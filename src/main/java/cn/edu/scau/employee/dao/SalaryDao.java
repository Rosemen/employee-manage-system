package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.common.annotation.Log;
import cn.edu.scau.employee.entity.Salary;
import cn.edu.scau.employee.mapper.SalaryExtendMapper;
import cn.edu.scau.employee.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/3/7 22:00
 */
@Component
public class SalaryDao {

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private SalaryExtendMapper salaryExtendMapper;


    public List<Salary> findByEmpNo(String empNo, Integer year, Integer month) {
        return salaryExtendMapper.selectByEmpNo(empNo, year, month);
    }

    @Log(table = "salary", type = 1)
    public Long insert(Salary salary) {
        salaryMapper.insert(salary);
        return salary.getId();
    }

    @Log(table = "salary", type = 2)
    public int deleteByIds(List<Long> ids) {
        return salaryExtendMapper.deleteByIds(ids);
    }

    @Log(table = "salary", type = 3)
    public int updateById(Salary salary) {
        return salaryMapper.updateById(salary);
    }
}
