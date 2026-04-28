package org.kariya.chapter01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sync {

    public static void main(String[] args) {
        // 同步
        readFile();
        // 异步
        new Thread(Sync::readFile).start();
        log.debug("do other things...");
    }


    public static void readFile() {
        log.debug("模拟读取文件...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
