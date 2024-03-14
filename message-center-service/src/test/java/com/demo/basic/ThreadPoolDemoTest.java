package com.demo.basic;


import com.demo.netty.core.SelfThreadPoolExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.RejectedExecutionException;

@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.SAME_THREAD)
public class ThreadPoolDemoTest {
    @Order(1)
    @Test
    public void testRejectedExecution() {
        Assertions.assertThrows(RejectedExecutionException.class, () -> {
            SelfThreadPoolExecutor selfThreadPoolExecutor = new SelfThreadPoolExecutor();
            for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 4 + 1; i++) {
                selfThreadPoolExecutor.submit(ThreadPoolDemoTest::sayHello);
            }
        });
    }

    @Order(2)
    @Test
    public void testSuccess() {
        SelfThreadPoolExecutor selfThreadPoolExecutor = new SelfThreadPoolExecutor();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 4; i++) {
            // 10 + 4 * 8
            selfThreadPoolExecutor.submit(ThreadPoolDemoTest::sayHello);
        }
    }

    private static void sayHello() {
        System.out.println("hello, world!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
