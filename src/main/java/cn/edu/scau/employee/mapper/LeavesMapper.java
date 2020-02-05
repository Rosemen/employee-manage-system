package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Leaves;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 请假Mapper
 */
public interface LeavesMapper {

    /**
     * 删除请假记录

     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加请假记录
     *
     * @param record
     * @return
     */
    int insert(Leaves record);

    /**
     * 查询请假记录
     *
     * @param id
     * @return
     */
    Leaves selectById(Long id);

    /**
     * 更新请假记录
     *
     * @param record
     * @return
     */
    int updateById(Leaves record);

}