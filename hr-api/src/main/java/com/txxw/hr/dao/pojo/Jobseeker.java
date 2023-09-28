package com.txxw.hr.dao.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_jobseeker")
@ApiModel(value="Jobseeker对象", description="")
public class Jobseeker implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "求职者id",example = "1")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "民族id")
    @TableField("nationId")
    private Long nationId;

    @ApiModelProperty(value = "最高学历")
    @TableField("tiptopDegree")
    private String tiptopDegree;

    @ApiModelProperty(value = "应聘职位ID")
    @TableField("posId")
    private Long posId;

    @ApiModelProperty(value = "应聘类型")
    private String type;

    @ApiModelProperty(value = "应聘来源")
    private String source;

    @ApiModelProperty(value = "工作经验")
    private String experience;

    @ApiModelProperty(value = "期望薪资")
    @TableField("expectSalary")
    private Integer expectSalary;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "求职者当前状态：1通过、0不通过、2待定")
    private Integer status;

    @ApiModelProperty(value = "是否拉黑")
    @TableField("isBlack")
    private Boolean isBlack;

    @ApiModelProperty(value = "拉黑原因")
    @TableField("blackreason")
    private String blackreason;

    @ApiModelProperty(value = "投递时间")
    @TableField("sendTime")
    private Long sendTime;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "提交状态：0未提交，1已提交")
    private Boolean submit;

    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/shanghai")
    private LocalDate birthday;

    @ApiModelProperty(value = "籍贯")
    @TableField("nativePlace")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌")
    @TableField("politicId")
    private Long politicId;

    @ApiModelProperty(value = "最高学历院校")
    private String school;

    @ApiModelProperty(value = "所属专业/方向")
    private String specialty;

    @ApiModelProperty(value = "毕业年份")
    @TableField("graduateYear")
    private String graduateYear;

    @ApiModelProperty(value = "可到岗时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/shanghai")
    @TableField("postTime")
    private LocalDate postTime;

    @ApiModelProperty(value = "操作员")
    private String operator ;

    //查询出求职者的相关面试信息
    @ApiModelProperty(value = "面试信息")
    @TableField(exist = false)
    private Interview interview;

}
