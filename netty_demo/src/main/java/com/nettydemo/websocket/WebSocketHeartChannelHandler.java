package com.nettydemo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 心跳处理器
 * @author xymt
 */
public class WebSocketHeartChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String text = textWebSocketFrame.text();
        if(text.equals("heart")) {
            System.out.println("收到客户端的一个心跳消息！");
            //说明当前发送的是一个心跳消息，回复一个心跳消息
            channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("heart"));
        } else {
            //非心跳消息，当前ChannelHandler不处理，透传给后面的ChannelHandler处理
            channelHandlerContext.fireChannelRead(textWebSocketFrame);
        }
    }

}
