package com.wy.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wy
 * @company
 * @Classname LockSupportTest
 * @Description TODO
 */

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        // t1 处于running 状态
        Thread thread = new Thread(() -> {
            m1();
        }, "t1");
        thread.start();
        Thread.State state = thread.getState();
        System.out.println(state);
        // t2的话，就处于blocked状态  获取监视器的锁
        Thread thread1 = new Thread(() -> {
            m1();
        }, "t2");
        thread1.start();
        Thread.State state1 = thread1.getState();
        System.out.println(state1);


    }


    public static synchronized void m1() {
        System.out.println("11111111");
        int j = 0;
        for (int i = 10; i < 1000000; i++) {
            j = ++j;
            System.out.println(j);
        }
    }
}
