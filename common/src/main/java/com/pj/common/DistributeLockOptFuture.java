package com.pj.common;

import java.util.concurrent.*;

/**
 * 描述: 分布式锁Future实现
 * <p>
 *
 * @version 1.0
 * @created 2021/8/18 18:38
 */
public class DistributeLockOptFuture<T> implements Future<T> {

    private final BlockingQueue<T> lockResult = new ArrayBlockingQueue<>(1);

    private volatile int state = 0;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return state == 1;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return lockResult.take();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        final T lockRet = lockResult.poll(timeout, unit);
        if (lockRet == null) {
            throw new TimeoutException();
        }
        return lockRet;
    }

    public void accept(T t) {
        lockResult.offer(t);
        state = 1;
    }
}
