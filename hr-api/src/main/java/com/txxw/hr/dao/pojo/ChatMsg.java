package com.txxw.hr.dao.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息，聊天websocket功能消息类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    private String from;

    private String to;

    private String content;

    private LocalDateTime date;

    private String formNickName;

}
