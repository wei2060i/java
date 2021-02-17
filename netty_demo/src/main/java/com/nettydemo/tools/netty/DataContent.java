package com.nettydemo.tools.netty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataContent {
    /**
     * 动作类型
     */
    private Integer action;
    /**
     * 聊天 内容封装
     */
    private ChatMsg chatMsg;
    /**
     * 扩展字段 要签收的 ChatMsg id
     */
    private String extend;
}