package com.nettydemo.tools.netty;

/**
 * 客户端消息 动作类型
 * @author sky
 */
public enum MsgActionEnum {

    CONNECT(1, "第一次初始化连接"),
    CHAT(2, "聊天信息"),
    SIGNED(3, "消息签收"),
    KEEPALIVE(4, "客户端保持心跳");

    public final Integer type;
    public final String content;

    MsgActionEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

}
