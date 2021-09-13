package com.wy.learn.interrupt;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname InterruptApiDemo
 * @Description
 * 中断协商机制  其他线程不能直接stop当前线程，只能与当前线程协商来进行处理
 */

public class InterruptApiDemo {

    public static void main(String[] args) {
        m1();
    }
    public static void m1() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("-----isInterrupted() = true，程序结束。");
                    break;
                }
            }
            System.out.println("------hello Interrupt");
        }, "t1");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            thread.interrupt();// 打标记 中断状态置为true
        }).start();
    }


}
