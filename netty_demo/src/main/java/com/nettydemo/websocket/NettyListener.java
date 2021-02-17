package com.nettydemo.websocket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author xymt
 */
@Component
public class NettyListener implements ApplicationListener<ContextRefreshedEvent> {

    //这里  无法注入
    //@Autowired
    //private static WebSocketServer webSocketServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /**
         * 在web项目中（spring mvc），系统会存在两个容器，一个是root application context ,
         * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
         * 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免这种问题，
         * 我们可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理
         */
        ApplicationContext parent = event.getApplicationContext().getParent();
        if(parent == null) {
            new WebSocketServer().start();
        }
    }

}