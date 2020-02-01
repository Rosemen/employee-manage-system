package cn.edu.scau.employee.mapper;


import cn.edu.scau.employee.entity.User;

/**
 *
 * @author chen
 * @description 用户Mapper接口
 * @date 2019/11/11
 */
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 更新用户
     *
     * @param record
     * @return
     */
    int updateById(User record);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User selectById(Long id);

}
