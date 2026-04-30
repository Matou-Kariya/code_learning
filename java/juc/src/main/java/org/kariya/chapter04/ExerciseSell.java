package org.kariya.chapter04;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j
public class ExerciseSell {
    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }

    public static void main(String[] args) {
        TicketWindow tw = new TicketWindow(2000);
        List<Thread> list = new ArrayList<>();
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                int count = tw.sell(randomAmount());
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        log.debug("selled count:{}", sellCount.stream().mapToInt(c -> c).sum());
        log.debug("remain count:{}", tw.getCount());
    }
}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        }
        return 0;
    }
}