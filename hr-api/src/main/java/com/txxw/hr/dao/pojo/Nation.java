package com.txxw.hr.dao.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author Enci
 * @since 2023-08-23
 */
@Data
//无参构造
@NoArgsConstructor
//有参构造
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "name")
@TableName("tx_nation")
@ApiModel(value="Nation对象", description="")
public class Nation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "民族")
    @Excel(name = "民族")
    @NonNull//必填
    private String name;


}
