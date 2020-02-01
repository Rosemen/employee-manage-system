package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Resource;

/**
 * 资源Mapper
 *
 * @author chen
 * @date 2019/11/20
 */
public interface ResourceMapper {
    /**
     * 添加资源
     *
     * @param record
     * @return
     */
    int insert(Resource record);

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 修改资源
     *
     * @param record
     * @return
     */
    int updateById(Resource record);

    /**
     * 查询资源
     *
     * @param id
     * @return
     */
    Resource selectById(Long id);
}