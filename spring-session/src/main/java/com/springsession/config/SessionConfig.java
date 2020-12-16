package com.springsession.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author sky
 * @date 2020/12/15 13:55
 *
 启动类 @EnableRedisHttpSession 开启Session共享功能
     通过修改 domain信息,请求时携带 cookie
 */
@Configuration
public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("G_SESSION");
        cookieSerializer.setDomainName("localhost");
        return cookieSerializer;
    }


}
