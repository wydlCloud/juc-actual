package com.wy.test;

/**
 * @author wy
 * @company
 * @Classname Demo
 * @Description TODO
 */

public class Demo {

    static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            m1();
        }, "t1");

        Thread thread1 = new Thread(() -> {
            m1();
        });

        Thread thread2 = new Thread(() -> {
            m1();
        });
        thread.start();
        thread1.start();
        thread2.start();

        thread.join();
        thread1.join();
        thread2.join();

        System.out.println(i);

    }

    public static void m1() {
        for (int j = 0; j < 1000; j++) {
             i++;
        }
    }
}
