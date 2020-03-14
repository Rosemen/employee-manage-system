package cn.edu.scau.employee.service;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.SalaryExcelItem;
import cn.edu.scau.employee.common.model.request.SalaryAddRequest;
import cn.edu.scau.employee.common.model.request.SalaryQueryRequest;

import java.util.List;

/**
 * @author chen.jiale
 * @Description 薪资业务接口
 * @date 2020/3/7 20:59
 */
public interface SalaryService {

    /**
     * 查询所有员工的薪资信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    CommonResult query(SalaryQueryRequest request) throws Exception;

    /**
     * 添加员工薪资信息
     *
     * @param request
     * @return
     */
    CommonResult add(SalaryAddRequest request) throws Exception;

    /**
     * 删除薪资记录
     *
     * @param ids
     * @return
     */
    CommonResult deleteByIds(List<Long> ids);

    /**
     * 修改薪资信息
     *
     * @param id
     * @param request
     * @return
     */
    CommonResult updateById(Long id, SalaryAddRequest request) throws Exception;

    /**
     * 导入薪资excel
     *
     * @param bytes
     * @return
     */
    CommonResult upload(byte[] bytes) throws Exception;

    /**
     * 导出薪资excel
     *
     * @return
     */
    List<SalaryExcelItem> download() throws Exception;

}
