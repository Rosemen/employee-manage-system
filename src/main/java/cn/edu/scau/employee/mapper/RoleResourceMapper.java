package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * *
 *
 * @author chen
 * @description 角色资源Mapper
 * @date 2019/11/20
 */
public interface RoleResourceMapper {
    /**
     * 添加
     *
     * @param record
     * @return
     */
    int insert(RoleResource record);

    /**
     * 删除
     *
     * @param roleId
     * @param resourceId
     * @return
     */
    int deleteById(@Param("roleId") Long roleId,
                   @Param("resourceId") Long resourceId);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    int updateById(RoleResource record);

    /**
     * 查询
     *
     * @param roleId
     * @return
     */
    List<RoleResource> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色id删除
     *
     * @param roleIds
     * @return
     */
    int deleteByRoleIds(List<Long> roleIds);

    /**
     * 批量添加角色资源记录
     *
     * @param roleResources
     * @return
     */
    int batchInsert(List<RoleResource> roleResources);

    /**
     * 获取某个角色的资源列表
     *
     * @param roleId
     * @return
     */
    List<Long> selectResourceIdsByRoleId(Long roleId);

    /**
     * 根据资源id删除
     *
     * @param resourceIds
     * @return
     */
    int deleteByResourceIds(List<Long> resourceIds);
}