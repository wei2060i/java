package com.securityuaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @author sky
 * @date 2022/2/17 13:05
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;
    /**
     * 注入的是ClientDetailsServiceConfigurer
     */
    @Autowired
    private ClientDetailsService clientDetailsService;
    //@Autowired
    //private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    /**
     * @param security 令牌端点的url安全约束
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // /oauth/token_key /oauth/check_token    isAuthenticated()
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * @param clients 客户端详情配置
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("c1")
                .secret(new BCryptPasswordEncoder().encode("client1_secret"))
                //客户端拥有的客户端资源,和客户端 ResourceServerConfigurerAdapter 保持一致
                .resourceIds("adminResource")
                .scopes("all")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "client_credentials", "implicit")
                .redirectUris("http://www.baidu.com")
                .autoApprove(true);
        //.and().withClient();
    }

    /**
     * 框架默认的授权端点 URL链接有如下几个:
     * /oauth/authorize :授权端点
     * /auth/token :令牌端点
     * /oauth/confirm_access :用户确认授权提交的端点
     * /oauth/error:授权服务错误信息端点。
     * /oauth/check_token : 用于资源服务访问的令牌进行解析的端点
     * /oauth/token_key :使用jwt令牌需要用到的提供公有密钥的端点。
     * 需要注意的是,这几个授权端点应该被Spring Security保护起来只供授权用户访问。
     *
     * @param endpoints 配置令牌访问端点
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints //定制化授权端点 url
                .pathMapping("/oauth/token", "/login")
                //密码模式必须的认证管理器
                .authenticationManager(authenticationManager)
                //密码模式的用户管理器
                .userDetailsService(userDetailsService)
                //授权码服务
                //.authorizationCodeServices(authorizationCodeServices)
                //令牌管理服务
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        //运行令牌自动刷新
        services.setReuseRefreshToken(true);
        services.setTokenStore(tokenStore);
        //使用jwt令牌
        services.setTokenEnhancer(accessTokenConverter);
        //令牌默认有效期2小时
        services.setAccessTokenValiditySeconds(7200);
        //刷新令牌有效期3天
        services.setRefreshTokenValiditySeconds(3600);
        return services;
    }

    /**
     * 授权码模式的授权码存储方式 内存
     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new InMemoryAuthorizationCodeServices();
//    }

}
