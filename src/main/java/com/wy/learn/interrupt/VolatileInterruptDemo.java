package com.wy.learn.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname VolatileInterruptDemo
 * @Description 使用关键字中断线程
 */

public class VolatileInterruptDemo {
    public static void main(String[] args) {
        m1();
    }

    static volatile boolean isStop = false;


    public static void m1() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println("-----isStop = true，程序结束。");
                    break;
                }
                System.out.println("------hello isStop");
            }
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }

}
