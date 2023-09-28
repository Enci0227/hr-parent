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
 * 前端查询十问回答映射
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
@Data
public class AnswerVo {

    @ApiModelProperty(value = "回答的问题编号")
    private String questionId;

    @ApiModelProperty(value = "问题内容")
    private String question;

    @ApiModelProperty(value = "回答编号Id")
    private String answerId;

    @ApiModelProperty(value = "问题回答")
    private String answer;

    @ApiModelProperty(value = "问题回答附件")
    private String annexurl;

}
