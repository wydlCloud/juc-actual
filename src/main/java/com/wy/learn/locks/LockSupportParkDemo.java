package com.wy.learn.locks;

import com.wy.learn.ThreadTest;
import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wy
 * @company
 * @Classname LockSupportParkDemo
 * @Description 通知和唤醒
 * <p>
 * 优点是：类似于wait  notify 不需要顺序执行  而且不用显示加锁
 * 通行证  permit（Thread thread）
 * <p>
 * 通行证为1  上限为1  不会递增 和信号量不同
 * <p>
 * 因为没有顺序性，可以被唤醒，不会导致t1线程的阻塞，只要unpark执行，就会唤醒t1
 */

public class LockSupportParkDemo {


    public static void main(String[] args) throws InterruptedException {
        m2();

    }

    /**
     * 先唤醒  后等待  一样可以被唤醒 区别于其他的，要有先后顺序
     * @throws InterruptedException
     */
    public static void m2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println("被唤醒");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1L);

        LockSupport.unpark(t1);

        System.out.println(Thread.currentThread().getName() + " \t " + System.currentTimeMillis() + "---unpark over");

    }

    public static void m1() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "进行等待了");
            LockSupport.park();
//            LockSupport.park(); // 这种情况下会阻塞掉
            System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒了");
        }, "t1");
        t1.start();

        new Thread(() -> {
            LockSupport.unpark(t1);
//            LockSupport.unpark(t1);
            System.out.println("唤醒t1");
        }).start();
    }
}


