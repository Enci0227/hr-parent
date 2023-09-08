package com.txxw.hr.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/5
 * @description:条件筛选用到的页码筛选字段
 **/
@Data
@ApiModel(value="JobSeekerPageParam对象", description="用于筛选求职者信息的前端参数映射，除页号及每页显示条数外其余参数皆可为空，待定项之后会具体调整")
public class JobSeekerPageParam {

    @ApiModelProperty(value = "页码数",required = true)
    private int page = 1;

    @ApiModelProperty(value = "每页显示条数",required = true)
    private int pageSize = 10;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "年龄（待定）")
    private Integer age;

    @ApiModelProperty(value = "最高学历")
    private String tiptopDegree;

    @ApiModelProperty(value = "职位编号")
    private Long posId;

    @ApiModelProperty(value = "应聘类型")
    private String type;

    @ApiModelProperty(value = "应聘来源")
    private String source;

    @ApiModelProperty(value = "工作经验")
    private String experience;

    @ApiModelProperty(value = "薪资范围（待定）")
    private Integer expectSalary;
}
