package com.nettydemo.tools.netty;


import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * @author xymt
 */
public class UserChannelRel {

    public static HashMap<Long, Channel> manager = new HashMap<>();

    public static void put(Long senderId,Channel channel) {
        manager.put(senderId,channel);
    }

    public static Channel get(Long senderId) {
        return  manager.get(senderId);
    }

}
