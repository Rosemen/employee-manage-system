package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.SalaryExcelItem;
import cn.edu.scau.employee.common.model.request.*;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.config.excel.TableStyleStrategy;
import cn.edu.scau.employee.service.SalaryService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/3/7 15:55
 */
@Api(description = "薪资Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @ApiOperation(value = "分页查询员工薪资")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody SalaryQueryRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return salaryService.query(request);
    }

    @ApiOperation(value = "添加薪资信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody SalaryAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return salaryService.add(request);
    }

    @ApiOperation(value = "删除薪资信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return salaryService.deleteByIds(ids);
    }

    @ApiOperation(value = "更新薪资信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody SalaryAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return salaryService.updateById(id, request);
    }

    @ApiOperation(value = "导入员工薪资信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return salaryService.upload(file.getBytes());
    }

    @ApiOperation(value = "导出员工薪资信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = URLEncoder.encode("员工薪资", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream, SalaryExcelItem.class);
        ExcelWriter excelWriter = writerBuilder.build();
        WriteSheet writeSheet = EasyExcel.writerSheet()
                .registerWriteHandler(TableStyleStrategy.getStrategy())
                .build();
        List<SalaryExcelItem> items = salaryService.download();
        excelWriter.write(items, writeSheet);
        excelWriter.finish();
        outputStream.close();
    }
}
