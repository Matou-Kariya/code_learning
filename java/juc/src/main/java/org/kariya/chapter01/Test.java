package org.kariya.chapter01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * 创建线程的三种方式
 * */
@Slf4j
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        demo01();
        demo02();
        demo03();
        log.info("{},running", Thread.currentThread().getName());
    }

    public static void demo01() {
        Thread t1 = new Thread(() -> log.info("{},running", Thread.currentThread().getName()), "t1");
        t1.start();
    }

    public static void demo02() {
        Runnable runnable = () -> log.info("{},running", Thread.currentThread().getName());
        Thread t2 = new Thread(runnable, "t2");
        t2.start();
    }

    public static void demo03() throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft = new FutureTask<>(() -> {
            log.info("{},running", Thread.currentThread().getName());
            Thread.sleep(2000);
            return 100;
        });
        new Thread(ft, "t3").start();
        log.info("返回值:{}", ft.get());
    }

}
