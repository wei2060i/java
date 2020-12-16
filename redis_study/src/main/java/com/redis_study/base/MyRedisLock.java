package com.redis_study.base;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author sky
 * @date 2020/12/8 14:52
 */
@Component
public class MyRedisLock {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private RedisConnection redisConnection;
    private String lockSha; //缓存lua脚本
    private String lockLua = "--分布式锁的key值\n" +
            "local key = KEYS[1]\n" +
            "--分布式锁的value值\n" +
            "local value = ARGV[1]\n" +
            "--分布式锁的超时时间\n" +
            "local timeout = ARGV[2]\n" +
            "--尝试进行分布式锁的设置\n" +
            "local result = redis.call('setnx',key,value)\n" +
            "if result == 1 then\n" +
            "--如果分布式锁设置成功,设置锁的超时时间\n" +
            "redis.call('expire',key,timeout)\n" +
            "--返回加锁成功\n" +
            "return true end\n" +
            "--返回加锁失败\n" +
            "return false\n";
    private String unlockSha; //缓存lua脚本
    private String unlockLua = "--释放锁的key值\n" +
            "local key = KEYS[1]\n" +
            "--锁的value值\n" +
            "local value = ARGV[1]\n" +
            "--从锁中获得value值\n" +
            "local lockValue = redis.call('get', key)\n" +
            "if lockValue == value then\n" +
            "--说明锁是自己添加的,则进行删除\n" +
            "redis.call('del', key)\n" +
            "return true\n" +
            "end\n" +
            "return false";

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @PostConstruct
    public void init() {
        redisConnection = redisTemplate.getConnectionFactory().getConnection();
        //缓存lua脚本
        lockSha = redisConnection.scriptLoad(lockLua.getBytes());
        unlockSha = redisConnection.scriptLoad(unlockLua.getBytes());
    }

    /**
     * setnx 如果存在key,则返回0;如果不存在key,设置成功返回1
     *
     * @param key
     * @param timeout 单位秒
     * @return
     */
    public boolean Lock(String key, int timeout) {
        String uuid = UUID.randomUUID().toString();
        threadLocal.set(uuid);
        //加锁和设置过期时间,必须是原子的,否则可能死锁[设置过期时间前断电]。
        return redisConnection.evalSha(lockSha, ReturnType.BOOLEAN,
                1, key.getBytes(), uuid.getBytes(),
                (timeout + "").getBytes());
//             String lockV = redisTemplate.opsForValue().get("lock");
//             if (uid.equals(lockV)) {
//             //非原子操作,查询、判断加删除 需要一定时间,查询后,未删除前key过期,就可能会删除别人的锁
//             redisTemplate.delete("lock");
//             }
    }

    public boolean unLock(String key) {
        String uuid = threadLocal.get();
        return redisConnection.evalSha(unlockSha, ReturnType.BOOLEAN,
                1, key.getBytes(), uuid.getBytes());
    }
}