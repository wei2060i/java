package com.jwt.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class JwtController {

    @RequestMapping("/testAll")
    @ResponseBody
    public Object testAll(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        JWTResult result = JWTUtils.validateJWT(token);

        JWTResponseData responseData=new JWTResponseData();
        if(result.isSuccess()){
            responseData.setCode(200);
            responseData.setData(result.getClaims().getSubject());
            String newToken=JWTUtils.createJWT(result.getClaims().getId(),
                    result.getClaims().getIssuer(),
                    result.getClaims().getSubject(),
                    1000*10);
            responseData.setToken(newToken);
        }else {
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
        }
        return responseData;
    }
    @RequestMapping("/login")
    @ResponseBody
    public Object login(String name,String pass){
        JWTResponseData responseData=new JWTResponseData();
        if(JWTUsers.isLogin(name,pass)){
            JWTSubject subject=new JWTSubject(name);
            String token = JWTUtils.createJWT(UUID.randomUUID().toString(), "wei",
                    JWTUtils.generalSubject(subject), 1000 * 10);
            responseData.setCode(200);
            responseData.setData(null);
            responseData.setMsg("登录成功");
            responseData.setToken(token);
        }else {
            responseData.setCode(500);
            responseData.setData(null);
            responseData.setMsg("登录失败");
            responseData.setToken(null);
        }
        return  responseData;
    }
    @RequestMapping("/jwt")
    public String toJwt(){
        return "jwt";
    }
}
