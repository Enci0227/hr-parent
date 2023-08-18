package com.txxw.hr.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
 * @since 2023-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "path")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "图标")
    @TableField("iconCls")
    private String iconCls;

    @ApiModelProperty(value = "是否保持激活")
    @TableField("keepAlive")
    private Boolean keepAlive;

    @ApiModelProperty(value = "是否要求权限")
    @TableField("requireAuth")
    private Boolean requireAuth;

    @ApiModelProperty(value = "父id")
    @TableField("parentId")
    private Integer parentId;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)//表示无需映射表中字段
    private List<Menu> children;

    //根据访问的url查询出哪些角色可以访问
    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)
    private List<Role> roles;


}
