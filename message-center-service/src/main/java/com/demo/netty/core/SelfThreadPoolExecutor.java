package com.demo.netty.core;

import io.netty.util.concurrent.DefaultThreadFactory;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelfThreadPoolExecutor extends ThreadPoolExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelfThreadPoolExecutor.class);
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final ThreadFactory THREAD_FACTORY = new DefaultThreadFactory(SelfThreadPoolExecutor.class, false);
    private static final AbortPolicy ABORT_POLICY = new AbortPolicy();

    /*
    * if using ArrayBlockingQueue or LinkedBlockingQueue with capacity may get error as:
    *   java.util.concurrent.RejectedExecutionException: Task com.demo.netty.core.SelfThreadPoolExecutor
    *
    * PriorityBlockingQueue will automatically expand capacity, if using this kind of blocking queue, maximumPoolSize
    * will be invalid. Since queue can load all following tasks, no need create new thread.
    *
    * PriorityBlockingQueue needs tasks implement Comparable interface, otherwise can not calculate priority for tasks.
    * */
    private static final ArrayBlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<>(10);


    public SelfThreadPoolExecutor() {
        super(CORE_POOL_SIZE, CORE_POOL_SIZE * 4, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, BLOCKING_QUEUE, THREAD_FACTORY, ABORT_POLICY);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        LOGGER.info("thread pool state: queue size: {}, active size:{}", this.getQueue().size(), this.getActiveCount());
        LOGGER.info("start executing: {}, on thread: {}", r.getClass().getSimpleName(), t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) {
            LOGGER.error("Failed to finished execute: {}", r.getClass().getSimpleName(), t);
        } else {
            LOGGER.info("Success finished execute: {}", r.getClass().getSimpleName());
        }
        super.afterExecute(r, t);
    }

    @Override
    public Future<?> submit(@Nonnull Runnable task) {
        PriorityTask<?> priorityTask = new PriorityTask<>(task);
        execute(priorityTask);
        return priorityTask;
    }

    private static class PriorityTask<T> extends FutureTask<T> implements Comparable<PriorityTask<T>>, Runnable {
        private final Runnable task;
        private final long priority;

        public PriorityTask(Runnable task) {
            super(task, null);
            this.task = task;
            priority = System.currentTimeMillis();
        }


        @Override
        public int compareTo(PriorityTask<T> o) {
            long diff = o.priority - priority;
            return diff > 0 ? 1 : diff < 0 ? -1 : 0;
        }

        @Override
        public void run() {
            task.run();
        }
    }
}
