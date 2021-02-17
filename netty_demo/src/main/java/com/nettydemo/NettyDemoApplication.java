package com.nettydemo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.nettydemo.dao")
public class NettyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyDemoApplication.class, args);
//        try {
//            new WebSocketServer().start();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

}
