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
    User selectByUsername(String username);

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
     */
    int deleteByIds(List<Integer> ids);

    /**
     * 根据工号或姓名查询
     *
     * @param username
     * @param name
     * @return
     */
    List<User> selectByUsernameOrName(@Param("username") String username,
                                      @Param("name") String name);
}
