package com.wy.learn.locks;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @company
 * @Classname FairAndNoFairDemo
 * @Description 公平和非公平锁
 */

public class FairAndNoFairDemo {
    private int number = 50;
    // ReentrantLock就支持公平锁和非公平锁  默认为false 是非公平锁，非公平锁可以提交吞吐量
    // 当然带来性能的基础上，就失去了公平性，谁能得到就谁先跑，不分先来后到
    private ReentrantLock lock = new ReentrantLock(false);

    public void sale() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出第: " + (number--) + "\t 还剩下: " + number);
            }
        } finally {
            lock.unlock();
        }


    }
}

class SaleTicketDemo {
    public static void main(String[] args) {
        FairAndNoFairDemo ticket = new FairAndNoFairDemo();

        new Thread(() -> {
            for (int i = 1; i <= 55; i++) ticket.sale();
        }, "a").start();
        new Thread(() -> {
            for (int i = 1; i <= 55; i++) ticket.sale();
        }, "b").start();
        new Thread(() -> {
            for (int i = 1; i <= 55; i++) ticket.sale();
        }, "c").start();
        new Thread(() -> {
            for (int i = 1; i <= 55; i++) ticket.sale();
        }, "d").start();
        new Thread(() -> {
            for (int i = 1; i <= 55; i++) ticket.sale();
        }, "e").start();
    }
}

