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
 * @since 2023-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_answer")
@ApiModel(value="Answer对象", description="")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目回答Id")
    private Long id;

    @ApiModelProperty(value = "求职者Id")
    @TableField("jobseekerId")
    private Long jobseekerId;

    @ApiModelProperty(value = "题号Id")
    @TableField("questionId")
    private Long questionId;

    @ApiModelProperty(value = "求职者回答")
    private String answer;

    @ApiModelProperty(value = "求职者回答附件链接")
    private String annexurl;

}
