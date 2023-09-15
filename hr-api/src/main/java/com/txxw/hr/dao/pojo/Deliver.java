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
 * @since 2023-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_deliver")
@ApiModel(value="Deliver对象", description="")
public class Deliver implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "投递编号id")
    private Long id;

    @ApiModelProperty(value = "求职者id")
    @TableField("jobseekerId")
    private Long jobseekerId;

    @ApiModelProperty(value = "投递时间")
    private Long createDate;


}
