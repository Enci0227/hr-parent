package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/7
 * @description:
 **/
@Data
@ApiModel(value="InfokeyParam对象", description="更新问卷基本信息字段所需要的参数")
public class InfokeyParam {

    @ApiModelProperty(value = "问卷编号id(1社招2校招3实习)",required = true)
    private Long testpaperId;

    @ApiModelProperty(value = "基本信息字段id集合",required = true)
    private List<Long> infokeyIds;
}
