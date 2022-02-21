package com.skyadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author sky
 * @date 2022/2/18 9:59
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ADMIN = "adminResource";

    @Autowired
    private TokenStore tokenStore;

    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http:127.0.0.1:8080/oauth/check_token");
        services.setClientId("c1");
        services.setClientSecret("client1_secret");
        return services;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ADMIN)
                //.tokenServices(tokenServices())
                //jwt方式 解析token
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //校验请求
        http.csrf().disable().authorizeRequests()
                //路径匹配规则
                .antMatchers("/**")
                //需要匹配 scope
                .access("#oauth2.hasScope('all')")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
