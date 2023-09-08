package com.txxw.hr.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页公共返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ResultPage对象", description="数据总条数及对应集合")
public class ResultPage {
    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数")
    private Long total;
    /**
     * 数据list
     */
    @ApiModelProperty(value = "数据集合")
    private List<?> data;
}
