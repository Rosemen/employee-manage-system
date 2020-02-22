package cn.edu.scau.employee.common.request;

import cn.edu.scau.common.constant.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description 员工信息查询参数类
 * @date 2020/2/2 17:12
 */
@ApiModel(value = "UserDetailQueryRequest", description = "员工信息查询条件")
@Data
public class UserDetailQueryRequest {

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @NotNull
    @ApiModelProperty(value = "分页信息")
    private PageConstant page;
}
