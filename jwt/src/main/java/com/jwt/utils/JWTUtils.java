package com.jwt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JWTUtils {
    //服务器的key。用于加解密的key数据，如果可以使用客户端生成的key,当前定义可以不使用
    private static final String JWT_SECERT="test_jwt";
    private static final ObjectMapper MAPPER=new ObjectMapper();
    private static final int JWT_ERRCODE_EXPIRE =1005;//token 过期
    private static final int JWT_ERRCODE_FAIL =1005;//验证不通过

   //验证jwt
    public static JWTResult validateJWT(String jwtStr){
        JWTResult checkResult=new JWTResult();
        Claims claims=null;
        try {
            claims=parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        }catch (ExpiredJwtException e){
            checkResult.setErrCode(JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        }catch (SignatureException e){
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }catch (Exception e){
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * @param jwtStr 服务器为客户端生成的签名数据  就是token
     * @return
     */
    private static Claims parseJWT(String jwtStr) {
        SecretKey secretKey=generalKey(); //密匙
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtStr)
                .getBody();//获取payload中 保存的所有的claims
    }

    /** 签发 jwt  创建token
     * @param id  jwt唯一身份标识，主要作为一次性token，回避重放攻击
     * @param iss  jwt签发者
     * @param subject jwt所面向的用户。就是Payload里面公开的数据，当前测试用的用户名
     * @param ttlMillis 有效期  毫秒
     * @return  token 是一次性的。是为一个用户的有效登录周期准备的一个token。用户退出或超时  token失效
     */
    public static String createJWT(String id,String iss,String subject,long ttlMillis){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;///加密算法
        long nowMillis =System.currentTimeMillis(); //当前数据
        Date now=new Date(nowMillis);
        SecretKey secretKey=generalKey(); //密匙
        JwtBuilder builder= Jwts.builder()
                .setId(id) //设置身份标识，唯一标识客户端。使用用户主键id或ip
                .setIssuer(iss)
                .setSubject(subject)
                .setIssuedAt(now) //token生成的时间
                .signWith(signatureAlgorithm,secretKey);
        if(ttlMillis >=0){
            long expMillis=nowMillis+ttlMillis;
            Date expDate=new Date(expMillis); //token 失效时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }
    public static SecretKey generalKey(){
        try {
            //byte[] encodeKey=Base64.decode(JWT_SECERT)
            byte[] encodeKey = JWT_SECERT.getBytes("UTF-8");
            SecretKey key=new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
            return key;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /** 生成 subject信息
     * @param subObj  要转换的对象
     * @return  java对象转json字符串
     */
    public static String generalSubject(Object subObj){
        try {
            return MAPPER.writeValueAsString(subObj);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }
}
