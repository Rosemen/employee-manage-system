package cn.edu.scau.employee.mapper;


import cn.edu.scau.employee.entity.UserDetail;

/**
 * @author chen.jiale
 * @date 2020/01/21
 * @description
 */
public interface UserDetailMapper {

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加用户信息
     *
     * @param record
     * @return
     */
    int insert(UserDetail record);

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    UserDetail selectById(Long id);

    /**
     * 更新用户信息
     *
     * @param record
     * @return
     */
    int updateById(UserDetail record);

}