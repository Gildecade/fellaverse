package com.fellaverse.backend.delay;

import com.fellaverse.backend.service.LimitedProductShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class DelayQueueManager implements CommandLineRunner {

    private final DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

    @Autowired
    @Lazy
    private LimitedProductShopService limitedProductShopService;

    /**
     * Add delay task to delay queue
     *
     * @param task to be added
     */
    public void put(DelayTask task) {
        log.info("Added delay task：{}", task);
        delayQueue.put(task);
    }

    /**
     * remove delay task
     *
     * @param task to be removed
     * @return true if this queue changed as a result of the call
     */
    public boolean remove(DelayTask task) {
        log.info("Removed delay task：{}", task);
        return delayQueue.remove(task);
    }

    /**
     * remove delay task
     *
     * @param taskId of task, aka identifier
     * @return true if this queue changed as a result of the call
     */
    public boolean remove(String taskId) {
        return remove(new DelayTask(new TaskBase(taskId), 0));
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Initialized delay queue");
        Executors.newScheduledThreadPool(50).execute(new Thread(this::executeThread));
    }

    /**
     * thread function to execute task
     */
    private void executeThread() {
        while (true) {
            try {
                DelayTask task = delayQueue.take();
                // execute task
                processTask(task);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * @param task to be processed
     */
    private void processTask(DelayTask task) {
        String identifier = task.getData().getIdentifier();
        TaskBase data = task.getData();
        log.info("Executing delay task：{}, on {}", identifier, new Date(task.getExpire()));
        if (data instanceof OrderTask orderTask) {
            limitedProductShopService.expire(orderTask.getOrderId());
        }
    }
}