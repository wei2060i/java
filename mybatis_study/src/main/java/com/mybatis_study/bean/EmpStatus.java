package com.mybatis_study.bean;

import lombok.Getter;

/**
 * 希望数据库保存的是100,200这些状态码，而不是默认0,1或者枚举的名
 *
 * @author lfy
 */
public enum EmpStatus {
    LOGIN(100, "用户登录"),
    LOGOUT(200, "用户登出"),
    REMOVE(300, "用户不存在");
    @Getter
    private Integer code;
    @Getter
    private String msg;

    EmpStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //按照状态码返回枚举对象

    public static EmpStatus getEmpStatusByCode(Integer code) {
        switch (code) {
            case 100:
                return LOGIN;
            case 300:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }
}
