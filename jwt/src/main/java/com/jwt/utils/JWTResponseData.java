package com.jwt.utils;
/*
发送 给客户端的数据
 */
public class JWTResponseData {
    private Integer code; //返回码  类似于 http响应码
    private Object data;//业务数据
    private String msg;//返回描述
    private String token; //身份标识  jwt令牌

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
