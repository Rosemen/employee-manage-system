package cn.edu.scau.employee.common.request;

import cn.edu.scau.common.constant.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/1/22 12:14
 */
@Data
@ApiModel(value = "DeptQueryRequest", description = "部门查询request")
public class DeptQueryRequest {

    @ApiModelProperty(value = "部门名")
    private String name;

    @NotNull
    @ApiModelProperty(value = "分页信息")
    private PageConstant page;
}
