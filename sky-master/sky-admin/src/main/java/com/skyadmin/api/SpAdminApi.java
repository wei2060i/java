package com.skyadmin.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sky
 * @date 2022/2/6 16:25
 */
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/spAdmin")
public class SpAdminApi {

    private static final Logger logger = LoggerFactory.getLogger(SpAdminApi.class);


    @GetMapping("t1")
    @PreAuthorize("hasAuthority('admin')")
    public String test() {
        return "ok1";
    }

    @Autowired
    private TokenStore tokenStore;

    @GetMapping("t2")
    @PreAuthorize("hasAuthority('admin')")
    public String test2() {
        return "ok2";
    }

    @DeleteMapping("/revokeToken")
    public Object logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer ", "").trim();
            //System.out.println(tokenValue);
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            System.out.println("_" + accessToken.getValue());

            tokenStore.removeAccessToken(accessToken);

            OAuth2AccessToken accessToken1 = tokenStore.readAccessToken(tokenValue);
            System.out.println("_" + accessToken1.getValue());
            System.out.println("移除............,");
        }
        return "ok";
    }

}