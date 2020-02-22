package cn.edu.scau.employee.common.request;

import cn.edu.scau.common.constant.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 11:29
 */
@ApiModel(value = "UserQueryRequest", description = "用户查询条件")
@Data
public class UserQueryRequest {

    @ApiModelProperty(value = "用户名")
    private String username;

    @NotNull
    @ApiModelProperty(value = "分页信息")
    private PageConstant page;
}
