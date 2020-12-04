package com.jwt.utils;

import java.util.HashMap;
import java.util.Map;
/*
模拟用户数据，实际开发访问数据库验证用户
 */
public class JWTUsers {
    private static final Map<String,String> USERS=new HashMap<>();
    static {
        for (int i=0;i<10;i++){
            USERS.put("admin"+i,"pass"+i);
        }
    }
    public static boolean isLogin(String username,String password){
        if(null ==username || username.trim().length()==0){
            return false;
        }
        String obj=USERS.get(username);
        if(obj==null || !obj.equals(password))
            return false;
        return true;
    }
}
