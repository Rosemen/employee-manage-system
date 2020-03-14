package cn.edu.scau.employee.mapper;


import cn.edu.scau.employee.entity.BusinessTrip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 出差扩展Mapper
 */
public interface BusinessTripExtendMapper {

    /**
     * 查询某年某月某员工的出差记录
     *
     * @param year
     * @param month
     * @param empNo
     * @return
     */
    List<BusinessTrip> selectByEmpNoAndMonth(@Param("year") Integer year,
                                             @Param("month") Integer month,
                                             @Param("empNo") String empNo);

    /**
     * 查询某年某季度某员工的出差记录
     *
     * @param year
     * @param quarter
     * @param empNo
     * @return
     */
    List<BusinessTrip> selectByEmpNoAndQuarter(@Param("year") Integer year,
                                               @Param("quarter") Integer quarter,
                                               @Param("empNo") String empNo);


    /**
     * 查询出差记录
     *
     * @param empNo
     * @param status
     * @return
     */
    List<BusinessTrip> selectByEmpNoAndStatus(@Param("empNo") String empNo, @Param("status") Integer status);
}