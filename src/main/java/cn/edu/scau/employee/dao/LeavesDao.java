package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.common.annotation.Log;
import cn.edu.scau.employee.entity.Leaves;
import cn.edu.scau.employee.mapper.LeavesExtendMapper;
import cn.edu.scau.employee.mapper.LeavesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/6 22:09
 */
@Component
public class LeavesDao {

    @Autowired
    private LeavesMapper leavesMapper;

    @Autowired
    private LeavesExtendMapper leavesExtendMapper;

    public List<Leaves> findByEmpNoAndMonth(Integer year, Integer month, String empNo) {
        return leavesExtendMapper.selectByEmpNoAndMonth(year, month, empNo);
    }

    public List<Leaves> findByEmpNoAndQuarter(Integer year, Integer quarter, String empNo) {
        return leavesExtendMapper.selectByEmpNoAndQuarter(year, quarter, empNo);
    }

    @Log(table = "leaves", type = 1)
    public int add(Leaves leave) {
        return leavesMapper.insert(leave);
    }

    public List<Leaves> findByEmpNoAndStatus(String empNo, Integer status) {
        return leavesExtendMapper.selectByEmpNoAndStatus(empNo, status);
    }
}
