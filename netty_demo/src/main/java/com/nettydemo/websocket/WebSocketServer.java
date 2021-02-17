package com.nettydemo.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WebSocketServer {
    private  EventLoopGroup master ;
    private  EventLoopGroup slave;
    private  ChannelFuture future;
    private  ServerBootstrap bootstrap = new ServerBootstrap();
    public void start() {
        master = new NioEventLoopGroup();
        slave = new NioEventLoopGroup();
        try {
            bootstrap
                    .group(master, slave)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();

                            //服务器只需要用到 请求的解码 和 响应的编码
                            //后续的ChannelHandler收到的就是 HttpRequest HttpContent LastHttpContent
                            pipeline.addLast(new HttpRequestDecoder());
                            pipeline.addLast(new HttpResponseEncoder());

                            //设置Http请求的集合器，简单来说就是将HttpRequest和HttpContent和LastHttpContent聚合成一个完整的Http对象
                            //换而言之，就是当前解码器之后的ChannelHandler接收到的就是一个FullHttpRequest
                            pipeline.addLast(new HttpObjectAggregator(1024 * 1024));

                            //WebSocketServerProtocolHandler
                            //1、将Http请求升级成为WebSocket协议
                            //2、处理客户端所有的非数据帧（状态帧、close、ping、pong）
                            //abc 是前端H5 的访问 uri 格式
                            pipeline.addLast(new WebSocketServerProtocolHandler("/abc"));

                            //配置一个心跳超时的ChannelHandle
                            //当10s钟以内，服务器没有收到客户端任何读消息时，就会自动的将这个Channel判定为掉线，踢出整个列表
                            pipeline.addLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS));

                            //自定义一个心跳的ChannelHandler处理器
                            // pipeline.addLast(new WebSocketHeartChannelHandler());
                            // 自定义一个Http请求的处理对象
                            pipeline.addLast(new WebSocketChannelHandler());
                        }
                    });

            future = bootstrap.bind(8081).syncUninterruptibly();
            System.out.println("http服务器已经启动,绑定端口为:8081");
        } catch (Exception e) {
             e.printStackTrace();
             master.shutdownGracefully();
             slave.shutdownGracefully();
             System.err.println("netty启动异常");
        }

    }

}
