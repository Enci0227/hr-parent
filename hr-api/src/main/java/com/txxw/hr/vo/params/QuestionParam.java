package com.txxw.hr.vo.params;

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
public class QuestionParam implements Serializable {

    @ApiModelProperty(value = "问题id(可为空，为空则新建问题)")
    private Long id;

    @ApiModelProperty(value = "问题归属(1社招2校招3实习)",required = true)
    private Long type;

    @ApiModelProperty(value = "问题详情",required = true)
    private String question;


}
