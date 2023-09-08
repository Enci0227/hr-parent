package com.txxw.hr.vo;

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
 * @since 2023-08-30
 */
@Data
@ApiModel(value="QuestionVo", description="求职者十问问题及回答")
public class QuestionVo implements Serializable {


    private String id;

    private String question;

    private String createDate;

    private String user;

}
