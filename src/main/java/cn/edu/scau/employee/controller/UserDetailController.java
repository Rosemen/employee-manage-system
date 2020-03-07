package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.employee.common.model.UserDetailExcelItem;
import cn.edu.scau.employee.common.model.request.UserDetailAddRequest;
import cn.edu.scau.employee.common.model.request.UserDetailQueryRequest;
import cn.edu.scau.employee.common.util.ValidatorUtil;
import cn.edu.scau.employee.config.excel.TableStyleStrategy;
import cn.edu.scau.employee.service.UserDetailService;
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
 * @date 2020/2/2 15:55
 */
@Api(description = "员工信息Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/info")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @ApiOperation(value = "分页查询员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public CommonResult query(@RequestBody UserDetailQueryRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return userDetailService.findByName(request);
    }

    @ApiOperation(value = "添加员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody UserDetailAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return userDetailService.add(request);
    }

    @ApiOperation(value = "删除员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return userDetailService.deleteByIds(ids);
    }

    @ApiOperation(value = "更新员工信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UserDetailAddRequest request) throws Exception {
        ValidatorUtil.validate(request);
        return userDetailService.updateById(id, request);
    }

    @ApiOperation(value = "导入用户信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return userDetailService.upload(file.getBytes());
    }

    @ApiOperation(value = "导出用户信息")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = URLEncoder.encode("员工信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream, UserDetailExcelItem.class);
        ExcelWriter excelWriter = writerBuilder.build();
        WriteSheet writeSheet = EasyExcel.writerSheet()
                .registerWriteHandler(TableStyleStrategy.getStrategy())
                .build();
        List<UserDetailExcelItem> items = userDetailService.download();
        excelWriter.write(items, writeSheet);
        excelWriter.finish();
        outputStream.close();
    }
}
