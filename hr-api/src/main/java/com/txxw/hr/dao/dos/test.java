package com.txxw.hr.dao.dos;

import lombok.Data;

/**
 * @authoer:沐羽千茗
 * @createDate:2023/8/10
 * @description:说明该文件夹下文件的作用
 * //dos:do对象,也是数据库查询出来的对象，但是这些对象不需要持久化，例子如下
 **/

@Data
public class test {
    private Integer year;

    private Integer month;

    private Long count;
}
