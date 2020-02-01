package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 用户操作扩展Mapper
 * @date 2019/12/22 16:34
 */
public interface UserExtendMapper {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    List<User> selectByUsername(String username);

    /**
     * 查询所有
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);

    /**
     * 更新用户角色
     *
     * @param roleId
     * @param roleIds
     * @return
     */
    int updateByRoleIds(@Param("roleId") Long roleId,
                        @Param("roleIds") List<Long> roleIds);
}
