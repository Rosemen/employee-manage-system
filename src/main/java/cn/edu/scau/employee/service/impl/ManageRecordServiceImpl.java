package cn.edu.scau.employee.service.impl;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.entity.ManageRecord;
import cn.edu.scau.employee.service.ManageRecordService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chen.jiale
 * @Description 操作日志业务接口实现类
 * @date 2019/12/22 16:30
 */
@Service
public class ManageRecordServiceImpl implements ManageRecordService {
    @Override
    public CommonResult batchInsert(List<ManageRecord> manageRecords) {
        return null;
    }

    @Override
    public CommonResult deleteByManageDate(Date manageDate) {
        return null;
    }

    @Override
    public ManageRecord selectById(Long valueOf) {
        return null;
    }
}
