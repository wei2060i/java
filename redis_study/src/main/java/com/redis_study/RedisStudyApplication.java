package com.redis_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RedisStudyApplication {

    /**
     * 哪些数据适合放入缓存?
     * 及时性、数据一致性要求不高
     * 访问量大,更新频率不高的数据
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(RedisStudyApplication.class, args);
    }

}
