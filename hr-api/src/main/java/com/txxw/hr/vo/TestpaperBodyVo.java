package com.txxw.hr.vo;

import com.txxw.hr.dao.pojo.Annexurl;
import com.txxw.hr.dao.pojo.Answer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/7
 * @description:
 **/
@ApiModel(value="TestPaperVo对象", description="天巡十问问卷作答结果")
@Data
public class TestpaperBodyVo {

    @ApiModelProperty(value = "求职者基本信息")
    JobseekerVo jobseekerVo;

    @ApiModelProperty(value = "求职者十问回答内容")
    List<AnswerVo> answerList;

    @ApiModelProperty(value = "求职者简历及作品集链接",notes = "作品集链接采用英文,分隔")
    Annexurl annexurl;
}
