package com.demo.basic;


import com.demo.netty.core.SelfThreadPoolExecutor;
import org.junit.jupiter.api.Test;

public class ThreadPoolDemoTest {

    @Test
    public void test() {

        SelfThreadPoolExecutor selfThreadPoolExecutor = new SelfThreadPoolExecutor();
        for (int i = 0; i < 500; i++) {
            selfThreadPoolExecutor.submit(ThreadPoolDemoTest::sayHello);
        }
        while (selfThreadPoolExecutor.getActiveCount() > 0) {

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
