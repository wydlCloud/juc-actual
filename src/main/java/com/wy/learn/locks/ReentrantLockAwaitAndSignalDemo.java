package com.wy.learn.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @company
 * @Classname ReentrantLockAwaitAndSigalDemo
 * @Description lock  unlock  await() signal()要结合使用  不然会出现和synchronized一样的问题  也要保证顺序  也必须在其中使用
 */

public class ReentrantLockAwaitAndSignalDemo {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        m2();
//        m3();

    }

    /**
     * wait 和notify的顺序不能错，不然会导致此线程不能被唤醒，而一直阻塞在那，但是其他的线程可以继续获取锁资源
     */
    public static void m3() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println("come in");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.println("线程结束");
        }, "t1").start();


        new Thread(() ->
        {
            lock.lock();
            try {
                System.out.println("come in 1");
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() ->

        {
            lock.lock();
            try {
                System.out.println("又获取到锁喽");
            } finally {
                lock.unlock();
            }
        }).start();

    }

    /**
     * 会抛异常出来  IllegalMonitorStateException 所以要好synchronized结合起来使用，所以说，这种情况下，就会存在阻塞点
     * Exception in thread "t1" Exception in thread "t2" java.lang.IllegalMonitorStateException
     * at java.lang.Object.wait(Native Method)
     * at java.lang.Object.wait(Object.java:502)
     * at com.wy.learn.locks.WaitAndNotifyDemo.lambda$m2$3(WaitAndNotifyDemo.java:74)
     * at java.lang.Thread.run(Thread.java:748)
     * java.lang.IllegalMonitorStateException
     * at java.lang.Object.notify(Native Method)
     * at com.wy.learn.locks.WaitAndNotifyDemo.lambda$m2$4(WaitAndNotifyDemo.java:87)
     * at java.lang.Thread.run(Thread.java:748)
     */
    public static void m2() {
        new Thread(() -> {
//            lock.lock();
            try {
                System.out.println("come in");
                condition.await();
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                lock.unlock();
            }
            System.out.println("线程结束");
        }, "t1").start();


        new Thread(() -> {
//            lock.lock();
            try {
                System.out.println("come in 1");
                condition.signal();
            } finally {
//                lock.unlock();
            }
        }, "t2").start();
    }

    /**
     * 铁三角， 正如上面描述的场景
     */
    public static void m1() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("come in");
                condition.await();
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("线程结束");
        }, "t1").start();


        new Thread(() ->
        {
            lock.lock();
            try {
                System.out.println("come in 1");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
