package cn.edu.scau.employee.controller;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.FtpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chen.jiale
 * @Description 文件管理Controller
 * @date 2020/2/29 21:54
 */
@Api(description = "文件管理Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FtpUtil ftpUtil;

    private static final String PICTURE_PATH = "/picture";

    private static final String FILE_PATH = "/file";

    private static final int PICTURE = 1;

    private static final int FILE = 2;

    @ApiOperation(value = "上传文件或图片")
    @ApiImplicitParam(name = "employee-token", value = "用于登录认证的token", paramType = "header", dataType = "string")
    @RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
    public CommonResult upload(@PathVariable("type") Integer type, @RequestParam(value = "file") MultipartFile file) throws Exception {
        String url = "";
        if (type == PICTURE) {
            url = ftpUtil.uploadFile(PICTURE_PATH, PICTURE_PATH, file.getOriginalFilename(), file.getInputStream());
        } else if (type == FILE) {
            url = ftpUtil.uploadFile(FILE_PATH, FILE_PATH,file.getOriginalFilename(), file.getInputStream());
        }
        return CommonResult.success(url);
    }
}
