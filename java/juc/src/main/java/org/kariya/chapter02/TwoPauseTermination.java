package org.kariya.chapter02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwoPauseTermination {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.start();
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        monitor.stop();
    }
}

@Slf4j
class Monitor {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                if (monitor.isInterrupted()) {
                    log.debug("被打断,料理后事...");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.info("监控日志中...");
                } catch (InterruptedException e) {
                    // 手动打断后，重新设置打断标记
                    monitor.interrupt();
                    e.printStackTrace();
                }
            }
        }, "monitor");
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
