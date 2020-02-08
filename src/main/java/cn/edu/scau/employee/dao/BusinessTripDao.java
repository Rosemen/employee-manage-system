package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.BusinessTrip;
import cn.edu.scau.employee.mapper.BusinessTripExtendMapper;
import cn.edu.scau.employee.mapper.BusinessTripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/8 20:18
 */
@Component
public class BusinessTripDao {

    @Autowired
    private BusinessTripMapper businessTripMapper;

    @Autowired
    private BusinessTripExtendMapper businessTripExtendMapper;

    public List<BusinessTrip> findByEmpNoAndMonth(Integer year, Integer month, Long empNo) {
        return businessTripExtendMapper.selectByEmpNoAndMonth(year, month, empNo);
    }

    public List<BusinessTrip> findByEmpNoAndQuarter(Integer year, Integer quarter, Long empNo) {
        return businessTripExtendMapper.selectByEmpNoAndQuarter(year, quarter, empNo);
    }
}
