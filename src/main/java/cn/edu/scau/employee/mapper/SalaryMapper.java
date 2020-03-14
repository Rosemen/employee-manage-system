package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Salary;

/**
 * @author chen.jiale
 * @date 2020/03/07 21:48:00
 * @description 薪资Mapper
 */
public interface SalaryMapper {

    /**
     * 删除薪资信息
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 插入薪资信息
     *
     * @param record
     * @return
     */
    int insert(Salary record);

    /**
     * 查询薪资信息
     *
     * @param id
     * @return
     */
    Salary selectById(Long id);

    /**
     * 修改薪资信息
     *
     * @param record
     * @return
     */
    int updateById(Salary record);

}