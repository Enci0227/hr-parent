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

    @ApiModelProperty(value = "姓名",notes = "姓名支持模糊查询")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "年龄范围",notes = "注意:传入范围的两个值，均不能为空，否则无效，最小值可以传0，最大值可以传入一个不可能出现的值")
    private Integer age[];

    @ApiModelProperty(value = "精确年龄查询",notes = "该字段用于具体年龄的传参")
    private Integer specificAge;

//    @ApiModelProperty(value = "年龄范围(起始)")
//    private Integer startage;
//    @ApiModelProperty(value = "年龄范围(截止)")
//    private Integer endage;

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

    @ApiModelProperty(value = "薪资范围",notes = "注意:此种解决方案传入范围的两个值均不能为空，否则无效，特殊情况最小值可以传0，最大值可以传入一个不可能出现的值")
    private Integer expectSalary[];

    @ApiModelProperty(value = "是否黑名单")
    private Boolean isBlack;

    @ApiModelProperty(value = "手机号",notes = "支持模糊查询")
    private String phone;

    @ApiModelProperty(value = "面试安排选项",notes = "选择一面则查询一面通过的")
    private Integer interviewSelect;

    @ApiModelProperty(value = "状态",notes = "0不通过1通过2待定")
    private Integer status;

}
