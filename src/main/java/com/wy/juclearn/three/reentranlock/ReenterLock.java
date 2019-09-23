package com.wy.juclearn.three.reentranlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2019/9/23.
 * Title: Simple
 * Description:
 * 重入锁可以替代synchronized关键字，在jdk1.5以及之前的版本中，重入锁的性能远远好于synchronized关键字，但是jdk1.6做了一些优化之后
 * 两者性能差不多。
 * <p>
 * 为什么叫做重入锁：
 * 一个线程可以连续两次以及多次获得同一把锁，这是允许的，如果不允许这么操作，那么同一个线程在第二次获得锁时就会和自己产生死锁。
 * 程序就会卡死在第二次申请锁的过程中，但需要注意的是，如果同一个线程多次获得锁，那么在释放锁的时候，也必须释放相同次数的锁。
 * 如果释放锁的次数过多，那么会得到IllegalMonitorStateException异常，反之，如果锁释放的少了，那么相当于当前线程还在持有这把锁，
 * 因此，其他线程还是进入不到临界区，访问不了临界区资源。
 * <p>
 * lock():  获得锁，如果锁已经被占用，则进行等待
 * lockInterruptibly():获得锁，但优先中断响应
 * tryLock(): 尝试获得锁，如果成功，返回true，失败返回false，该方法不等待，立即返回
 * tryLock(long time,TimeUnit unit):在给定时间内尝试获得锁
 * unlock()：释放锁
 * 就重入锁的实现来看，他主要集中在java层面，属于是显示锁。
 * 在重入锁的实现中，主要包含三个要素：
 * 1：原子状态
 * 2.等待队列  所有没有请求到锁的线程会进入等待队列进行等待。待有线程释放锁后，系统就能从等待队列中唤醒一个线程，继续工作。
 * 3.阻塞原语 park和unpark  用来挂起和恢复线程 在线程阻塞工具类：lockSupport中
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000000; j++) {
            //使用重入锁保护临界资源i，确保多线程对i操作的安全性。与synchronized相比，重入锁有着显示的操作的过程。
            //开发人员必须手动指定何时加锁和释放锁。也正因为这样，重入锁对逻辑的控制的灵活性要远远好于synchronized。、
            //需要注意的就是在退出临界区的时候，必须记得释放锁，否则其他线程就没有机会再访问临界区了。
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread thread = new Thread(reenterLock);
        Thread thread1 = new Thread(reenterLock);
        thread.start();
        thread1.start();
        //阻塞调用此方法的线程(calling thread)进入 TIMED_WAITING 状态，直到线程t完成，此线程再继续
        thread.join();
        thread1.join();
        System.out.println(i);
        System.out.println("主线程执行结束");


    }
}
