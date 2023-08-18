package com.txxw.hr.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:向前端返回内容Result的统一数据格式
 **/
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String msg;

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
