package com.jwt.utils;

import io.jsonwebtoken.Claims;
/*
  结果对象
 */
public class JWTResult {
    // 200  正确
    private int errCode;
    // 结果是否成功
    private boolean success;
    // 验证过程中Payload中的数据
    private Claims claims;

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean isSuccess(){
        return success;
    }
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
