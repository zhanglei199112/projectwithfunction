package com.example.demo.redis.lock;

public interface RedisLock {
    public boolean tryLock(long time);
    public void unlock();
}
