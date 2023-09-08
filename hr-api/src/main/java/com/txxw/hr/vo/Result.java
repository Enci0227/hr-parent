package com.txxw.hr.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:向前端返回内容Result的统一数据格式
 **/
@Data
@AllArgsConstructor
@ApiModel(value="Result对象", description="返回给前端数据的固定格式")
public class Result {

    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private Object data;


    /**
     * 成功返回
     * @param data
     * @return
     */
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }

    /**
     * 失败返回
     * @param code
     * @param msg
     * @return
     */
    public static Result fail(int code, String msg){
        return new Result(false,code,msg,null);
    }
}
