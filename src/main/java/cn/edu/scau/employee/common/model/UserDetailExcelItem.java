package cn.edu.scau.employee.common.model;

import cn.edu.scau.employee.common.constant.GenderConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author chen.jiale
 * @Description
 * @date 2020/2/23 22:38
 */
@Data
public class UserDetailExcelItem implements Serializable {

    private static final long serialVersionUID = -2681990221426393702L;

    @ExcelProperty(index = 0, value = "工号")
    private String empNo;

    @ExcelProperty(index = 1, value = "姓名")
    private String name;

    @ExcelProperty(index = 2, value = "性别", converter = GenderConverter.class)
    private Integer gender;

    @ExcelProperty(index = 3, value = "生日")
    private Date birthday;

    @ExcelProperty(index = 4, value = "入职时期")
    private Date hiredate;

    @ExcelProperty(index = 5, value = "手机号")
    private String phone;

    @ExcelProperty(index = 6, value = "邮箱")
    private String email;

    @ExcelProperty(index = 7, value = "地址")
    private String address;

    @ExcelProperty(index = 8, value = "学历")
    private String education;

    @ExcelProperty(index = 9, value = "部门")
    private String dept;

    @ExcelProperty(index = 10, value = "职位")
    private String position;

}
