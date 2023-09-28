package com.txxw.hr.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tx_question")
@ApiModel(value="Question对象", description="")
public class Question implements Serializable{

    @ApiModelProperty(value = "天巡十问问题Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "天巡十问问题归属（1社招2校招3实习）")
    private Long type;

    @ApiModelProperty(value = "天巡十问问题详情")
    private String question;

    @ApiModelProperty(value = "修改时间")
    private Long createDate;

    @ApiModelProperty(value = "修改人")
    private String user;

}
