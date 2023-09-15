package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/11
 * @description:
 **/
@Data
public class AddBlackParam {

    @ApiModelProperty(value = "求职者id",required = true)
    private Long jobseekerId;

    @ApiModelProperty(value = "拉黑原因")
    private String blackreason;
}
