package com.redis_study;


import com.redis_study.base.MyRedisLock;
import org.junit.jupiter.api.Test;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisStudyApplicationTests {
    @Resource
    private MyRedisLock myRedisLock;
    @Resource
    private RedissonClient redissonClient;

    /**
     * 闭锁
     */
    @Test
    public void countDownLatch() {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.trySetCount(5);
        try {
            door.await(); //等待完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //其他线程获取锁,计数减一
        RCountDownLatch userDoor = redissonClient.getCountDownLatch("door");
        userDoor.countDown();//计数减一
    }

    /**
     * getSemaphore信号量,park是redis的key,里面是个数
     *   boolean b = park.tryAcquire();
     *   park.acquireAsync()
     *   park.acquire(10);
     *   park.tryAcquire(1,TimeUnit.SECONDS);
     *   ...
     *   park.release();
     *   park.releaseAsync()
     */
    @Test
    public void semaphoreLock() {
        RSemaphore park = redissonClient.getSemaphore("park");
        try {
            park.acquire(); //获取
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            park.release(); //释放

        }
    }

    @Test
    public void readWriteLock() {
        RReadWriteLock readwrite = redissonClient.getReadWriteLock("readwrite");
        RLock rLock = readwrite.readLock();
        rLock.lock();
        rLock.unlock();
        rLock.lock(10,TimeUnit.SECONDS);//10秒后自动解锁,写锁和这个一样
        try {
            //尝试加锁,最多等待100秒,加锁成功后,10秒和自动解锁,写锁和这个一样
            boolean b = rLock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RLock writeLock = readwrite.writeLock();
        writeLock.unlock();
    }

    /**
     * redissonClient.lock() 默认30s,业务执行不完自动续期。
     * 30s 是看门狗LockWatchdogTimeout的默认时间,
     * 阻塞等待,一直占锁成功后,启动定时任务,30s / 3的时间重新更新续期为30s
     * my_lock.lock(10, TimeUnit.SECONDS);指定过期时间,不会自动续期。
     */
    @Test
    public void testLock() {
        RLock my_lock = redissonClient.getLock("my_lock");
        try {
            // my_lock.lock();
            my_lock.lock(10, TimeUnit.SECONDS);//10秒自动释放
            //等待3秒加锁,如果加锁成功,10秒自动释放
            boolean b = my_lock.tryLock(3, 10, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            my_lock.unlock();
        }
    }

    /**
     * 异步方法执行
     */
    @Test
    public void async(){
        RLock my_lock = redissonClient.getLock("my_lock");
        try {
            my_lock.lockAsync();
            my_lock.lockAsync(10,TimeUnit.SECONDS);//异步加锁,10秒自动释放
            //等待3秒加锁,如果加锁成功,10秒自动释放
            boolean b = my_lock.tryLock(3, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            my_lock.unlock();
        }
    }

    /**
     * 公平锁
     */
    @Test
    public void fairLock() {
        RLock fair = redissonClient.getFairLock("fair");
        try {
            // fair.lock();
            fair.lock(10, TimeUnit.SECONDS);//10秒自动释放
            //等待3秒加锁,如果加锁成功,10秒自动释放
            boolean b = fair.tryLock(3, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fair.unlock();
        }
    }
    /**
     * 联锁 Redisson的RedissonMultiLock对象可以将多个RLock对象关联为一个联锁
           每个RLock对象实例可以来自于不同的Redisson实例。
     */
    @Test
    public void multiLock(RedissonClient redissonClient, RedissonClient redissonClient2){
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient2.getLock("lock1");
        RedissonMultiLock redissonMultiLock = new RedissonMultiLock(lock1, lock2);
        try {
            //同时加锁,都加锁成功才算成功
            redissonMultiLock.lock();
            //等待3秒加锁,如果加锁成功,10秒自动释放
            redissonMultiLock.tryLock(1,10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redissonMultiLock.unlock();
        }
    }

    /**
     * 红锁
     * RedissonMultiLock对象可以将多个RLock对象关联为一个联锁
     * 每个RLock对象实例可以来自于不同的Redisson实例。
     */
    @Test
    public void redLock(RedissonClient redissonClient, RedissonClient redissonClient2){
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient2.getLock("lock1");
        RedissonRedLock redissonRedLock = new RedissonRedLock(lock1, lock2);
        try {
            //同时加锁,大部分节点加锁成功 才算成功
            redissonRedLock.lock();
            //等待3秒加锁,如果加锁成功,10秒自动释放
            redissonRedLock.tryLock(1,10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redissonRedLock.unlock();
        }
    }

    /**
     * Redisson的可过期性信号量(PermitExpirableSemaphore)实在RSemaphore对象的基础上
     * 为每个信号增加了一个过期时间。
     * 每个信号可以通过独立的ID来辨识,释放时只能通过提交这个ID才能释放。
     */
    @Test
    public void a() {
        RPermitExpirableSemaphore expirableSemaphore = redissonClient.getPermitExpirableSemaphore("expirableSemaphore");
        String permitId2 = null;
        try {
            String permitId1 = expirableSemaphore.acquire();
            //
            permitId2 = expirableSemaphore.acquire(1, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            expirableSemaphore.release(permitId2);
        }
    }
}