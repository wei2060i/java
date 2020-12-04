package com.jwt.utils;
/*
作为 subject数据使用。就是Payload里面公开的数据
不包含任何敏感数据
开发中建议使用实体类型 或BO  BTO数据对象
 */
public class JWTSubject {
    private String username;
    public JWTSubject(){
    }
    public JWTSubject(String username){
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
