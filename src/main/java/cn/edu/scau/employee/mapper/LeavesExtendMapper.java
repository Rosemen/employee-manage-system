package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Leaves;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 请假Mapper
 */
public interface LeavesExtendMapper {

    /**
     * 查询某员工某月的请假记录
     *
     * @param year
     * @param month
     * @param empNo
     * @return
     */
    List<Leaves> selectByEmpNoAndMonth(@Param("year") Integer year,
                                       @Param("month") Integer month,
                                       @Param("empNo") String empNo);

    /**
     * 查询某员工某年某季度的请假记录
     *
     * @param year
     * @param quarter
     * @param empNo
     * @return
     */
    List<Leaves> selectByEmpNoAndQuarter(@Param("year") Integer year,
                                         @Param("quarter") Integer quarter,
                                         @Param("empNo") String empNo);

    /**
     * 查询员工请假情况
     *
     * @param empNo
     * @param status
     * @return
     */
    List<Leaves> selectByEmpNoAndStatus(@Param("empNo") String empNo,
                                        @Param("status") Integer status);
}