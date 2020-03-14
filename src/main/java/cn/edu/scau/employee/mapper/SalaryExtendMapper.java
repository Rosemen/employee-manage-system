package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chen.jiale
 * @date 2020/03/07 21:48:00
 * @description 薪资扩展Mapper
 */
public interface SalaryExtendMapper {

    /**
     * 根据工号查询
     *
     * @param empNo
     * @param year
     * @param month
     * @return
     */
    List<Salary> selectByEmpNo(@Param("empNo") String empNo,
                               @Param("year") Integer year,
                               @Param("month") Integer month);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") List<Long> ids);
}