package org.kariya.chapter04;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestShareResource {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        demo02();
    }

    public static void demo01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter++;
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("计算完毕:{}", counter);
    }

    public static void demo02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (TestShareResource.class) {
                for (int i = 0; i < 50000; i++) {
                    counter++;
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (TestShareResource.class) {
                for (int i = 0; i < 50000; i++) {
                    counter++;
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("计算完毕:{}", counter);
    }
}
