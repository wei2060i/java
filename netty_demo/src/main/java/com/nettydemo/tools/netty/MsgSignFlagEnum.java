package com.nettydemo.tools.netty;

/**
 * @author xymt
 */

public enum MsgSignFlagEnum {

    unsign(false,"未签收"),
    signed(true,"已签收");

    public final Boolean type;
    public final String content;

    MsgSignFlagEnum(Boolean type,String content) {
        this.type = type;
        this.content = content;
    }
}
