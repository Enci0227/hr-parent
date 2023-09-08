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
 * 
 * </p>
 *
 * @author Enci
 * @since 2023-09-01
 */
@Data
@ApiModel(value="AnswerParam对象", description="天巡十问问题编号及对应作答")
public class AnswerParam implements Serializable {


    @ApiModelProperty(value = "十问问题编号",required = true)
    private Long questionId;

    @ApiModelProperty(value = "十问作答答案",required = true)
    private String answer;

    @ApiModelProperty(value = "十问作答问题作答附件")
    private String annexurl;

}
