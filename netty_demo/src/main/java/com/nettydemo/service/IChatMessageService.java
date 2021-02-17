package com.nettydemo.service;

import com.nettydemo.beans.po.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nettydemo.tools.netty.ChatMsg;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wei
 * @since 2020-03-19
 */
public interface IChatMessageService extends IService<ChatMessage> {

    Long saveChatMessage(ChatMsg chatMsg);

}
