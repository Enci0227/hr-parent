package com.txxw.hr.vo.params;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

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

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "姓名",required = true)
    private String name;

    @ApiModelProperty(value = "性别",required = true)
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "民族Id")
    private Long nationId;

    @ApiModelProperty(value = "民族名称",required = true,notes = "当传id时此字段可以无参")
    private String nation;

    @ApiModelProperty(value = "最高学历",required = true)
    private String tiptopDegree;

    @ApiModelProperty(value = "职位Id")
    private Long posId;

    @ApiModelProperty(value = "职位名称",required = true,notes = "当传id时此字段可以无参")
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

    //新增字段待添加
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌Id")
    private Long politicId;

    @ApiModelProperty(value = "政治面貌",required = true, notes = "当传id时此字段可以无参")
    private String politic;

    @ApiModelProperty(value = "最高学历院校")
    private String school;

    @ApiModelProperty(value = "所属专业/方向")
    private String specialty;

    @ApiModelProperty(value = "毕业年份")
    private String graduateYear;

    @ApiModelProperty(value = "可到岗时间")
    private LocalDate postTime;

    @ApiModelProperty(value = "提交状态")
    private Boolean submit;

}
