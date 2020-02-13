package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.ManageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author chen
 * @description 操作记录Mapper
 * @date 2019/11/16
 */
public interface ManageRecordMapper {

    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int insert(ManageRecord record);

    /**
     * 查询记录
     *
     * @param id
     * @return
     */
    ManageRecord selectById(Long id);

    /**
     * 更新记录
     *
     * @param record
     * @return
     */
    int updateById(ManageRecord record);


    /**
     * 删除
     *
     * @param manageDate
     * @return
     */
    int deleteById(Date manageDate);
}