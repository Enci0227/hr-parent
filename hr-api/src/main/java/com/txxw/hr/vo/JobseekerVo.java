package com.txxw.hr.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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

    private String resume;

    private String portfolio;

    private String notes;

    private Boolean submit;

}
