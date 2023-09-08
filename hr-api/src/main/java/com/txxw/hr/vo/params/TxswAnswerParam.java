package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/5
 * @description:天巡十问用户提交页
 **/

@Data
public class TxswAnswerParam {

    //求职者基本信息
    @ApiModelProperty(value = "求职者基本信息",required = true)
    private JobseekerParam jobseekerParam;

    //求职者有关天巡十问的作答
    @ApiModelProperty(value = "求职者有关天巡十问的作答",required = true)
    private List<AnswerParam> answerParamList;

    //简历作品集链接
    @ApiModelProperty(value = "简历作品集链接")
    private AnnexurlParam annexurlParam;

}
