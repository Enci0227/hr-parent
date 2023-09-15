package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/14
 * @description:
 **/
@ApiModel(value="UserStatusParam对象", description="用于用户状态的修改")
@Data
public class UserStatusParam {

    @ApiModelProperty(value = "用户id",required = true)
    private Long id;

    @ApiModelProperty(value = "状态",notes = "1通过2未通过3待定", required = true)
    private Integer status;


}
