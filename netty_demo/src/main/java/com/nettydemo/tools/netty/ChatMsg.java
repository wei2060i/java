package com.nettydemo.tools.netty;

import lombok.Data;

@Data
public class ChatMsg {
    /**
     * 发送者 用户id
     */
    private Long senderId;
    /**
     * 接收者用户id
     */
    private Long receiverId;
    /**
     * 聊天内容
     */
    private String msg;
    /**
     * 信息id  用于 信息签收
     */
    private Long msgId;
}
