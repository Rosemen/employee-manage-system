package cn.edu.scau.employee.service;


import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.entity.ManageRecord;

import java.util.Date;
import java.util.List;

/**
 * @author chen.jiale
 * @Description 操作日志业务接口
 * @date 2019/12/19 22:22
 */
public interface ManageRecordService {

    CommonResult batchInsert(List<ManageRecord> manageRecords);

    CommonResult deleteByManageDate(Date manageDate);

    ManageRecord selectById(Long valueOf);
}
