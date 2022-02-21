package com.skyadmin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yue WeiWei
 * @date 2022/2/19 22:14
 */
@RestController
public class Sso {

    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/my-logout")
    public Object logout(//@RequestParam("accessToken") String accessToken
                         HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer ", "").trim();
            System.out.println(tokenValue);
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
            System.out.println("移除............,");
        }
        return "ok";
    }

    @GetMapping("test")
    @PreAuthorize("hasAuthority('admin:')")
    public Object test() {
        return "22";
    }

}
