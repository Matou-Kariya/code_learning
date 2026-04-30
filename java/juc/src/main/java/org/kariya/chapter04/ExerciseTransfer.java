package org.kariya.chapter04;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ExerciseTransfer {
    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAmount());
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAmount());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("total:{},a:{},b:{}", a.getMoney() + b.getMoney(), a.getMoney(), b.getMoney());
    }
}

class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getMoney() {
        return this.balance;
    }

    public void transfer(Account account, int amount) {
        synchronized (Account.class) {
            if (this.balance >= amount) {
                this.setBalance(this.getMoney() - amount);
                account.setBalance(account.getMoney() + amount);
            }
        }
    }
}