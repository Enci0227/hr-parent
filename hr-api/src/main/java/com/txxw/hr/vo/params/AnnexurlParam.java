package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/5
 * @description:
 **/
@Data
@ApiModel(value="AnnexurlParam对象", description="求职者提交的简历及作品集链接")
public class AnnexurlParam {

    //简历链接
    @ApiModelProperty(value = "简历链接")
    private String resume;

    //作品集链接
    @ApiModelProperty(value = "作品集链接")
    private String portfolio;
}
