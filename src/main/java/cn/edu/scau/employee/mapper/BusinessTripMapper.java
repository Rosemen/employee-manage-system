package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.BusinessTrip;

/**
 * @author chen.jiale
 * @date 2020/02/02
 * @description 出差Mapper
 */
public interface BusinessTripMapper {
    /**
     * 删除出差记录
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加出差记录
     *
     * @param record
     * @return
     */
    int insert(BusinessTrip record);

    /**
     * 查询出差记录
     *
     * @param id
     * @return
     */
    BusinessTrip selectById(Long id);

    /**
     * 修改出差记录
     *
     * @param record
     * @return
     */
    int updateById(BusinessTrip record);

}