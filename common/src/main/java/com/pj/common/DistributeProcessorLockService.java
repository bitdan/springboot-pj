package com.pj.common;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 进程分布式锁服务
 * <p>
 *
 * @version 1.0
 * @created 2021/8/18 20:13
 */
@Component
@Slf4j
public class DistributeProcessorLockService {

    public static final String MASTER_LOCK = "MASTER_LOCK";

    private final RedissonClient redissonClient;
    BlockingQueue<LockOperation> queue = new LinkedBlockingQueue<>(10);

    public DistributeProcessorLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @PostConstruct
    public void init() {

        log.info("-----------------------------------------------启动进程分布锁服务-------------------------------------------------------");
        start();
    }

    public void start() {

        Thread thread = new Thread(this::processLockAction);
        thread.setDaemon(false);
        thread.start();
    }


    private void processLockAction() {
        for (; ; ) {
            try {
                LockOperation lockOperation = queue.take();
                log.debug("Distribute process lock loop trigger action  is " + lockOperation.getAction());
                if (lockOperation.getAction().equals("LOCK")) {
                    RLock master_name = redissonClient.getLock(lockOperation.getName());
                    boolean locked = master_name.tryLock(5, -1, TimeUnit.SECONDS);
                    lockOperation.operationFuture.accept(locked);
                } else if (lockOperation.getAction().equals("UNLOCK")) {
                    RLock master_name = redissonClient.getLock(lockOperation.getName());
                    master_name.unlock();
                    lockOperation.operationFuture.accept(true);
                } else {
                    log.error("Distribute process lock loop trigger: unsupport action - " + lockOperation.getAction());
                }
            } catch (Exception e) {
                log.warn("Distribute process lock loop trigger exception.", e);
            }
        }
    }

    public boolean lock(String name) {
        // return false;
        try {
            log.info("DistributeProcessorLockService.name is : {}", name);
            DistributeLockOptFuture<Boolean> future = new DistributeLockOptFuture<>();
            queue.offer(new LockOperation(name, "LOCK", future));
            return future.get();
        } catch (Exception e) {
            log.warn("Distribute lock [" + name + "] failed.", e);
            return false;
        }
    }

    public void unlock(String name) {
        try {
            DistributeLockOptFuture<Boolean> future = new DistributeLockOptFuture<>();
            queue.offer(new LockOperation(name, "UNLOCK", future));
            future.get();
        } catch (Exception e) {
            //do nothing
            log.warn("Distribute unlock [" + name + "] failed.", e);
        }
    }

    public boolean lockProcess() {

        return lock(MASTER_LOCK);
    }

    static class LockOperation {

        private final String name;

        private final String action;

        private final DistributeLockOptFuture<Boolean> operationFuture;


        public LockOperation(String name, String action, DistributeLockOptFuture<Boolean> future) {
            this.name = name;
            this.action = action;
            this.operationFuture = future;
        }

        public String getName() {
            return name;
        }

        public String getAction() {
            return action;
        }
    }
}
