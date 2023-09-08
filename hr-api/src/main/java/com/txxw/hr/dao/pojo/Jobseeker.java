package com.txxw.hr.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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

    @ApiModelProperty(value = "求职者id")
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
    @TableField("isBlock")
    private Boolean isBlock;

    @ApiModelProperty(value = "投递时间")
    @TableField("sendTime")
    private Long sendTime;

    @ApiModelProperty(value = "简历链接url")
    private String resume;

    @ApiModelProperty(value = "作品集链接url,可存储多个链接")
    private String portfolio;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "提交状态：0未提交，1已提交")
    private Boolean submit;


}
