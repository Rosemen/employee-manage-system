package cn.edu.scau.employee.mapper;

import cn.edu.scau.employee.entity.ManageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *
 * @author chen
 * @description 操作记录Mapper
 * @date 2019/11/16
 */
public interface ManageRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManageRecord record);

    int insertSelective(ManageRecord record);

    ManageRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManageRecord record);

    int updateByPrimaryKey(ManageRecord record);

    int batchInsert(@Param("manageRecords") List<ManageRecord> manageRecords);

    int deleteByManageDate(Date manageDate);
}