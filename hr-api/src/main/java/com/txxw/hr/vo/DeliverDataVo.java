package com.txxw.hr.vo;

import lombok.Data;

import java.util.List;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/9/13
 * @description:
 **/
@Data
public class DeliverDataVo {

    //提交次数
    private int count;

    //提交时间列表
    private List<String> createDate;
}
