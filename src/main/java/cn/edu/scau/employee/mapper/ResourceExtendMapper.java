package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源Mapper
 *
 * @author chen
 * @date 2019/11/20
 */
public interface ResourceExtendMapper {

    /**
     * 查询资源
     *
     * @param parentId
     * @return
     */
    List<Resource> selectByParentId(Long parentId);

    /**
     * 模糊查询资源
     *
     * @param name
     * @return
     */
    List<Resource> selectByName(String name);

    /**
     * 获取子菜单数量
     *
     * @param parentId
     * @return
     */
    int selectCountByParentId(Long parentId);

    /**
     * 获取子资源id列表
     *
     * @param ids
     * @return
     */
    List<Long> selectIdByParentIds(List<Long> ids);

    /**
     * 删除资源
     *
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);

    /**
     * 查询所有
     *
     * @return
     */
    List<Resource> selectAll();

    /**
     * 更新子资源的url
     *
     * @param url
     * @param id
     * @return
     */
    int updateUrlById(@Param("url") String url,
                            @Param("id") Long id);
}