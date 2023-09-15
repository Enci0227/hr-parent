package com.txxw.hr.vo.params;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 求职者提交基本信息表单数据
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Data
@ApiModel(value="JobseekerParam对象", description="求职者有关基本信息的作答")
public class JobseekerParam{

    @ApiModelProperty(value = "姓名",required = true)
    private String name;

    @ApiModelProperty(value = "性别",required = true)
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "民族名称",required = true)
    private String nation;

    @ApiModelProperty(value = "最高学历",required = true)
    private String tiptopDegree;

    @ApiModelProperty(value = "职位名称",required = true)
    private String position;

    @ApiModelProperty(value = "应聘类型",required = true)
    private String type;

    @ApiModelProperty(value = "应聘来源",required = true)
    private String source;

    @ApiModelProperty(value = "工作经验",required = true)
    private String experience;

    @ApiModelProperty(value = "期望薪资")
    private Integer expectSalary;

    @ApiModelProperty(value = "邮箱",required = true)
    private String mail;

    @ApiModelProperty(value = "手机号",required = true)
    private String phone;

    @ApiModelProperty(value = "备注")
    private String notes;

}
