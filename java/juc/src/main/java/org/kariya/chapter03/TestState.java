package org.kariya.chapter03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestState {
    public static void main(String[] args) {
        demo02();
    }

    public static void demo01() {
        Thread t1 = new Thread(() -> log.info("running..."), "t1");
        Thread t2 = new Thread(() -> {
            while (true) {
            }
        }, "t2");
        t2.start();
        Thread t3 = new Thread(() -> log.info("running..."), "t3");
        t3.start();
        Thread t4 = new Thread(() -> {
            synchronized (TestState.class) {
                try {
                    Thread.sleep(100000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t4");
        t4.start();
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t5");
        t5.start();
        Thread t6 = new Thread(() -> {
            synchronized (TestState.class) {
                try {
                    Thread.sleep(100000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t6");
        t6.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // t1 state NEW
        // t2 state RUNNABLE
        // t3 state TERMINATED
        // t4 state TIMED_WAITING
        // t5 state WAITING
        // t6 state BLOCKED
        log.info("t1 state {}", t1.getState());
        log.info("t2 state {}", t2.getState());
        log.info("t3 state {}", t3.getState());
        log.info("t4 state {}", t4.getState());
        log.info("t5 state {}", t5.getState());
        log.info("t6 state {}", t6.getState());
    }

    public static void demo02() {
        new Thread(() -> {
            log.info("洗水壶...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("烧开水...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("泡茶...");
        }).start();
        new Thread(() -> {
            log.info("洗茶壶...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("洗茶杯...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("拿茶叶...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
