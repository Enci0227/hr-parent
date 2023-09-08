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
 * @since 2023-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_annexurl")
@ApiModel(value="Annexurl对象", description="")
public class Annexurl implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "求职者id")
    @TableField("jobseekerId")
    private Long jobseekerId;

    @ApiModelProperty(value = "简历")
    private String resume;

    @ApiModelProperty(value = "作品集")
    private String portfolio;


}
