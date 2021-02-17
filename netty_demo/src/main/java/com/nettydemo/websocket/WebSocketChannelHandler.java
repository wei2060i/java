package com.nettydemo.websocket;

import com.alibaba.fastjson.JSONObject;
import com.nettydemo.service.IChatMessageService;
import com.nettydemo.tools.SpringUtil;
import com.nettydemo.tools.netty.ChatMsg;
import com.nettydemo.tools.netty.DataContent;
import com.nettydemo.tools.netty.MsgActionEnum;
import com.nettydemo.tools.netty.UserChannelRel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @author wei
 */
@Component
public class WebSocketChannelHandler extends SimpleChannelInboundHandler<Object> {

    private static final Log log = LogFactory.getLog(WebSocketChannelHandler.class);

    /**
     * 保存所有客户端的通道
     */
    public static final ChannelGroup USERS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private WebSocketServerHandshaker handShaker;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) {

        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame text = (TextWebSocketFrame) msg;
            String content = text.text();
            Channel currentChannel = channelHandlerContext.channel();

            DataContent dataContent = JSONObject.parseObject(content, DataContent.class);
            System.out.println("-------TextWebSocketFrame---------" + dataContent);

            Integer action = dataContent.getAction();
            if (action.equals(MsgActionEnum.CONNECT.type)) {
                //webSocket 第一次open的时候,初始化channel,把channel和uid关联
                Long senderId = dataContent.getChatMsg().getSenderId();
                if (!UserChannelRel.manager.containsKey(senderId)) {
                    UserChannelRel.put(senderId, currentChannel);
                    System.out.println("senderId" + senderId + "---currentChannel" + currentChannel);
                    System.out.println("UserChannelRel-size:" + UserChannelRel.manager.size());
                } else {
                    UserChannelRel.manager.remove(senderId);
                    UserChannelRel.put(senderId, currentChannel);
                    System.out.println("UserChannelRel-manager-size:" + UserChannelRel.manager.size());
                }
            } else if (action.equals(MsgActionEnum.CHAT.type)) {
                //保存聊天记录  同时 消息标记 未签收
                ChatMsg chatMsg = dataContent.getChatMsg();
                Long receiverId = chatMsg.getReceiverId();

                IChatMessageService chatMsgService = (IChatMessageService) SpringUtil.getBean("chatMessageServiceImpl");
                Long msgId = chatMsgService.saveChatMessage(chatMsg);
                //信息id 用于信息签收
                chatMsg.setMsgId(msgId);

                DataContent sendContent = new DataContent()
                        .setChatMsg(chatMsg)
                        .setAction(MsgActionEnum.CHAT.type);
                // 从全局用户channel关系中 获取接收方的 channel
                Channel receiverChannel = UserChannelRel.get(receiverId);
                if (receiverChannel == null) {
                    //用户离线 推送消息(JPush 个推  小米推送)
                } else {
                    Channel findChannel = USERS.find(receiverChannel.id());
                    if (findChannel != null) {
                        receiverChannel.writeAndFlush(
                                new TextWebSocketFrame(
                                        JSONObject.toJSONString(sendContent)));
                    } else {
                        //用户离线 推送消息(JPush 个推 小米推送)
                        //UserChannelRel
                    }
                }
            } else if (action.equals(MsgActionEnum.SIGNED.type)) {
                //签收 消息


            } else if (action.equals(MsgActionEnum.KEEPALIVE.type)) {
                //心跳
                Channel heart = channelHandlerContext.channel();
                DataContent data = new DataContent();
                data.setAction(MsgActionEnum.KEEPALIVE.type);

                heart.writeAndFlush(new TextWebSocketFrame(
                        JSONObject.toJSONString(data)));
            }

        } else if (msg instanceof BinaryWebSocketFrame) {
            log.error("二进制测试");
        } else if (msg instanceof PingWebSocketFrame) {
            //是否 Ping
            channelHandlerContext.channel().writeAndFlush(
                    new PingWebSocketFrame(((PingWebSocketFrame) msg).content().retain()));
            return;
        } else if (msg instanceof CloseWebSocketFrame) {
            //判断是否是关闭链路的指令
            handShaker.close(channelHandlerContext.channel(), ((CloseWebSocketFrame) msg).retain());
            return;
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        USERS.add(ctx.channel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("有一个客户端连接了服务器！");
        log.info("客户端地址:" + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.error("有一个客户端断开了和服务器的连接！");
        log.error("客户端地址:" + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        USERS.remove(ctx.channel());
        UserChannelRel.manager.entrySet().removeIf(stringIntegerEntry -> stringIntegerEntry
                .getValue().id().equals(ctx.channel().id()));
        log.error("handlerRemoved--managerSize:" + UserChannelRel.manager.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("通道发生异常:" + ctx.channel().remoteAddress().toString());
        USERS.remove(ctx.channel());
        UserChannelRel.manager.entrySet().removeIf(stringIntegerEntry -> stringIntegerEntry
                .getValue().id().equals(ctx.channel().id()));
        ctx.close();
    }

}
