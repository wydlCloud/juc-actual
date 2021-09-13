package com.wy.learn.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @company
 * @Classname ReEntryLockDemo
 * @Description 重入锁 又名递归锁
 * 指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用并且不发生死锁，这样的锁就叫做可重入锁。
 * 简单的来说就是：
 * <p>
 * 在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 *
 * 以下这几种情况都可以证明可重入锁
 *
 * 但是使用lock锁的话，如果少释放一个锁的话会出现什么情况呢
 *
 * 如果少释放一个锁，当前线程是可以拿到锁继续的，但是少释放一个锁，就会导致另外的线程，想使用这个锁对象，取不到锁，而一直阻塞
 */

public class ReEntryLockDemo {


    public static void main(String[] args) {

//        sync();
//        reentrantLock();
//        m1();
        m4();
    }


    public static Object object = new Object();

    public static synchronized void m4() {
        System.out.println("m4走了");
        m5();
    }

    public static synchronized void m5() {
        System.out.println("m5走了");
        m6();
    }

    public static synchronized void m6() {
        System.out.println("最后到m6走了");
        System.out.println("m6");
    }


    public static void m1() {
        synchronized (object) {
            m2();
            System.out.println("m1调用m2");
        }
    }

    public static void m2() {
        synchronized (object) {
            System.out.println("m2调用m3");
            m3();
        }
    }

    public static void m3() {
        synchronized (object) {
            System.out.println("m3自己调用自己");
        }
    }

    public static void reentrantLock() {

        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----外层");
                lock.lock();
                ;
                try {
                    System.out.println(Thread.currentThread().getName() + "\t" + "-----内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }

    static Object lock = new Object();

    public static void sync() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("外层");
                synchronized (lock) {
                    System.out.println("中层");
                    synchronized (lock) {
                        System.out.println("内层");
                    }
                }
            }
        }).start();
    }
}
