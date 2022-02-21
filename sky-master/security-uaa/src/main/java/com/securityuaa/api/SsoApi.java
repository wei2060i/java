package com.securityuaa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sky
 * @date 2022/2/19 17:41
 */
@RestController
public class SsoApi {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/login")
    public Object postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Map<String, Object> map = new HashMap<>();
        map.put("token", accessToken.getValue());
        map.put("refreshToken", accessToken.getRefreshToken().getValue());
        map.put("expiresIn", accessToken.getExpiresIn());
        map.put("tokenHead", "Bearer ");
        return map;
    }

    @GetMapping("a")
    public Object tst() {
        return "a";
    }


}
