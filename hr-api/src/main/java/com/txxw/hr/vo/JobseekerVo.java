package com.txxw.hr.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.txxw.hr.dao.pojo.Interview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 前端显示求职者信息映射实体
 * </p>
 *
 * @author Enci
 * @since 2023-08-30
 */
@Data
public class JobseekerVo {

    private String id;

    private String name;

    private String gender;

    private Integer age;

    private String nation;

    private String tiptopDegree;

    private String pos;

    private String type;

    private String source;

    private String experience;

    private Integer expectSalary;

    private String mail;

    private String phone;

    private Integer status;

    private Boolean isBlack;

    private String blackreason;

    private String sendTime;

    private String notes;

    private Boolean submit;

    //新增字段待添加
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌")
    private String politic;

    @ApiModelProperty(value = "最高学历院校")
    private String school;

    @ApiModelProperty(value = "所属专业/方向")
    private String specialty;

    @ApiModelProperty(value = "毕业年份")
    private String graduateYear;

    @ApiModelProperty(value = "可到岗时间")
    private LocalDate postTime;

    @ApiModelProperty(value = "操作员")
    private String operator;

    @ApiModelProperty(value = "面试相关信息")
    private Interview interview;

}
