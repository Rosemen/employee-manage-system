package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.ManageRecord;
import cn.edu.scau.employee.mapper.ManageRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/9 14:37
 */
@Component
public class ManageRecordDao {

    @Autowired
    private ManageRecordMapper manageRecordMapper;

    public int add(ManageRecord record) {
        return manageRecordMapper.insert(record);
    }
}
