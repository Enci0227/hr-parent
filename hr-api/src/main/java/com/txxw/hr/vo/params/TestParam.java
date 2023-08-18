package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:这里面的类是接收来自前端传过来的数据参数
 **/
@Data
@ApiModel(description="测试实体")
public class TestParam {

    @ApiModelProperty(value = "用户id2",example = "1")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;
}
