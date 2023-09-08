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
 * @since 2023-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_infokey")
@ApiModel(value="Infokey对象", description="")
public class Infokey implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "基本信息字段id")
    private Long id;

    @ApiModelProperty(value = "基本信息字段名称")
    private String name;

    @ApiModelProperty(value = "基本信息输入值的类型")
    private Integer type;

    @ApiModelProperty(value = "给求职者的提示信息")
    private String info;

    @ApiModelProperty(value = "对应基本信息表中的字段")
    private String keyname;

    @ApiModelProperty(value = "是否必填，0非必填1必填")
    private Boolean required;

    @ApiModelProperty(value = "如果是选择性字段请输入选择信息，使用/分隔")
    private String isSelect;



}
