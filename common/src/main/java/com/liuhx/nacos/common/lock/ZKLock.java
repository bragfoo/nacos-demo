package com.liuhx.nacos.common.lock;

import java.util.concurrent.TimeUnit;

public interface ZKLock {
    boolean lock(String lockName);
    boolean unlock(String lockName);
    boolean tryLock(String lockName,long timeout,TimeUnit unit);
}
