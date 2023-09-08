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
 * @since 2023-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tx_testpaper")
@ApiModel(value="Testpaper对象", description="")
public class Testpaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷编号")
    private Long id;

    @ApiModelProperty(value = "问卷类型")
    private String type;

    @ApiModelProperty(value = "天巡十问基本信息题目编号列表，英文逗号分隔")
    private String infolist;

    @ApiModelProperty(value = "天巡十问十问编号列表，英文逗号分隔")
    private String questionlist;


}
