package cn.edu.scau.employee.mapper;


import cn.edu.scau.employee.entity.UserDetail;

/**
 *
 * @author chen.jiale
 * @date 2020/01/21
 * @description
 */
public interface UserDetailMapper {

    int deleteById(Long id);

    int insert(UserDetail record);

    UserDetail selectById(Long id);

    int updateById(UserDetail record);

}