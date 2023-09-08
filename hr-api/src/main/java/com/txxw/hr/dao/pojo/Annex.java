package com.txxw.hr.dao.pojo;

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
 * @since 2023-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_annex")
@ApiModel(value="Annex对象", description="")
public class Annex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件id")
    private Integer id;

    @ApiModelProperty(value = "附件名称")
    private String name;

    @ApiModelProperty(value = "是否允许添加")
    private Boolean required;


}
