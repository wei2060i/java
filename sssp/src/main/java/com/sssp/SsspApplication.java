package com.sssp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages ={"com.repository"})
@EntityScan(basePackages="com.entity")
@EnableTransactionManagement
public class SsspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsspApplication.class, args);
    }

}
