package org.kariya.chapter02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestDaemon {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("守护线程....");
            }
        }, "t1");
        t1.setDaemon(true);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2000);
        log.info("main线程end...");
    }
}
