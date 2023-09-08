package com.txxw.hr.vo;

import com.txxw.hr.dao.pojo.Annex;
import com.txxw.hr.dao.pojo.Infokey;
import com.txxw.hr.dao.pojo.Question;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/7
 * @description:
 **/
@ApiModel(value="TestPaperVo对象", description="天巡十问问卷参数")
@Data
public class TestpaperVo {

    @ApiModelProperty(value = "基本信息字段列表")
    private List<Infokey> InfokeyList;

    @ApiModelProperty(value = "天巡十问问题列表")
    private List<QuestionVo> QuestionList;

    @ApiModelProperty(value = "附件添加选项")
    private List<Annex> AnnexList;
}
