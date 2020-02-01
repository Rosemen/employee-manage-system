package cn.edu.scau.employee.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/22 10:54
 */
@Data
@ApiModel(value = "DeptAddRequest", description = "部门添加request")
public class DeptAddRequest {

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
