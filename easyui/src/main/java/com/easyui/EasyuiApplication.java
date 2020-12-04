package com.easyui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com")
@MapperScan("com.dao")
@EnableTransactionManagement
public class EasyuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyuiApplication.class, args);
    }

}
