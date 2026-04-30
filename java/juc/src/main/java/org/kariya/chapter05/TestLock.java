package org.kariya.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestLock {

    // JDK15以后禁用偏向锁 ------
    public static void main(String[] args) {
        demo01();
    }

    public static void demo01() {
        Dog dog = new Dog();
        log.info("before lock:{}", ClassLayout.parseInstance(dog).toPrintable());
        new Thread(() -> {
            synchronized (dog) {
                log.info("locking:{}", ClassLayout.parseInstance(dog).toPrintable());
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (dog) {
                log.info("locking:{}", ClassLayout.parseInstance(dog).toPrintable());
            }
        }, "t2").start();
        log.info("after lock:{}", ClassLayout.parseInstance(dog).toPrintable());
    }

}

@Slf4j
class Dog {

}
