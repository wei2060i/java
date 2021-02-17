package com.nettydemo.service.impl;

import com.nettydemo.beans.po.ChatMessage;
import com.nettydemo.dao.ChatMessageDao;
import com.nettydemo.service.IChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nettydemo.tools.netty.ChatMsg;
import com.nettydemo.tools.netty.MsgSignFlagEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wei
 * @since 2020-03-19
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageDao, ChatMessage> implements IChatMessageService {


    @Override
    public Long saveChatMessage(ChatMsg chatMsg) {
        ChatMessage chatMessage = new ChatMessage()
                                      .setSendUid(chatMsg.getSenderId())
                                      .setAcceptUid(chatMsg.getReceiverId())
                                      .setMsg(chatMsg.getMsg())
                                      .setSignFlag(MsgSignFlagEnum.unsign.type);
        boolean insert = chatMessage.insert();
        if (!insert) {
            return null;
        }
        return chatMessage.getId();
    }

}
