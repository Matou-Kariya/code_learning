package org.kariya.chapter02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class TestPark {

    public static void main(String[] args) {
        demo01();
    }

    public static void demo01() {
        Thread t1 = new Thread(() -> {
            log.info("park...");
            LockSupport.park();
            log.info("unpark...");
            System.out.println("打断状态:{}" + Thread.currentThread().isInterrupted());
            //log.info("打断状态:{}", Thread.currentThread().isInterrupted());
            Thread.interrupted();
            // park线程被打断之后无法再park
            LockSupport.park();
            log.info("unpark...");
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();
    }
}
