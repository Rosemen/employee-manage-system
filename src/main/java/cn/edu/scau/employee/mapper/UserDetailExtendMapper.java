package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.UserDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/21 14:32
 */
public interface UserDetailExtendMapper {

    /**
     * 批量删除用户详情
     *
     * @param userDetailIds
     * @return
     */
    int deleteByIds(List<Long> userDetailIds);

    /**
     * 更新用户的部门id
     *
     * @param newDeptId
     * @param deptIds
     * @return
     */
    int updateByDeptIds(@Param("newDeptId") Long newDeptId,
                        @Param("deptIds") List<Long> deptIds);

    /**
     * 根据姓名查询
     *
     * @param name
     * @return
     */
    List<UserDetail> selectByName(String name);

    /**
     * 获取员工总数
     *
     * @return
     */
    int selectCount();

    /**
     * 获取所有员工工号
     *
     * @return
     */
    List<Long> selectAllEmpNos();
}
