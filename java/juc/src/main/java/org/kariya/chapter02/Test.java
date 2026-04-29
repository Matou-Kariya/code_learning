package org.kariya.chapter02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 线程的常用方法
 * */
@Slf4j
public class Test {

    public static void main(String[] args) {
        // demo01();
        // demo02();
        // demo03();
        // demo04();
        // demo05();
        demo07();
    }

    public static void demo01() {
        Thread t1 = new Thread(() -> log.info("running"), "t1");
        log.info("{} 线程状态 {}", t1.getName(), t1.getState().name());
        t1.start();
        log.info("{} 线程状态 {}", t1.getName(), t1.getState().name());
        // 线程不能同时start两次
        // t1.start();
    }

    public static void demo02() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1");
        log.info("{} 线程状态 {}", t1.getName(), t1.getState().name());
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{} 线程状态 {}", t1.getName(), t1.getState().name());
    }

    public static void demo03() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("{}线程被唤醒", Thread.currentThread().getName());
                throw new RuntimeException(e);
            }
        }, "t1");
        log.info("{} 线程状态 {}", t1.getName(), t1.getState().name());
        t1.start();
        try {
            // 可读性更高
            // TimeUnit.SECONDS.sleep(1);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 唤醒线程
        log.info("准备唤醒线程{}", t1.getName());
        t1.interrupt();
        log.info("{} 线程状态 {}", t1.getName(), t1.getState().name());
    }

    public static void demo04() {
        Thread t1 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                log.info("---->1 {}", count++);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                // 让出时间片
                // Thread.yield();
                log.info("---->2 {}", count++);
            }
        }, "t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }


    public static void demo05() {
        AtomicInteger i = new AtomicInteger();
        Thread t1 = new Thread(() -> {
            log.info("{}开始....", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("{}结束....", Thread.currentThread().getName());
            i.set(10);
        }, "t1");
        log.info("{}开始....", Thread.currentThread().getName());
        t1.start();
        // join等待调用线程执行结束
        try {
            t1.join();
            // 没等够时间返回0
            // t1.join(500);
            // 等超了时间，线程提前结束join也会提前结束
            // t1.join(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{}结束....{}", Thread.currentThread().getName(), i.get());
    }

    // 打断休眠中的线程
    public static void demo06() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("打断开始...");
        t1.interrupt();
        log.info("{}打断标记{}", t1.getName(), t1.isInterrupted());
    }

    // 打断正在运行的进程
    public static void demo07() {
        Thread t1 = new Thread(() -> {
            while (true) {

            }
        }, "t1");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("打断开始...");
        t1.interrupt();
        log.info("{}打断标记{}", t1.getName(), t1.isInterrupted());
    }
}
