package com.wy.learn.locks;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname WaitAndNotifyDemo
 * @Description 关于锁的问题，如果object 没有wait(),这个时候，下面的那个线程是进行阻塞状态，因为没有取到锁
 * <p>
 * 所以呢阻塞状态，如果进行wait(). 此线程会释放锁，等待其他线程获取锁， 唤醒后，继续去竞争锁，拿到锁来执行。
 * <p>
 * synchronized wait()  notify() 铁三角，  必须要去锁资源  不然会报错，可以试一下
 */

public class WaitAndNotifyDemo {
    static Object object = new Object();

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
            synchronized (object) {
                System.out.println("come in");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束");
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (object) {
                System.out.println("come in 1");
                object.notify();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (object) {
                System.out.println("又获取到锁喽");
            }
        }).start();
    }

    /**
     * 会抛异常出来  IllegalMonitorStateException 所以要好synchronized结合起来使用，所以说，这种情况下，就会存在阻塞点
     * Exception in thread "t1" Exception in thread "t2" java.lang.IllegalMonitorStateException
     * 	at java.lang.Object.wait(Native Method)
     * 	at java.lang.Object.wait(Object.java:502)
     * 	at com.wy.learn.locks.WaitAndNotifyDemo.lambda$m2$3(WaitAndNotifyDemo.java:74)
     * 	at java.lang.Thread.run(Thread.java:748)
     * java.lang.IllegalMonitorStateException
     * 	at java.lang.Object.notify(Native Method)
     * 	at com.wy.learn.locks.WaitAndNotifyDemo.lambda$m2$4(WaitAndNotifyDemo.java:87)
     * 	at java.lang.Thread.run(Thread.java:748)
     */
    public static void m2() {
        new Thread(() -> {
//            synchronized (object){
            System.out.println("come in");
            try {
                object.wait();
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束");
//            }
        }, "t1").start();


        new Thread(() -> {
//            synchronized (object){
            System.out.println("come in 1");
            object.notify();
//            }
        }, "t2").start();
    }

    /**
     * 铁三角， 正如上面描述的场景
     *
     */
    public static void m1() {
        new Thread(() -> {
            synchronized (object) {
                System.out.println("come in");
                try {
                    object.wait();
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束");
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (object) {
                System.out.println("come in 1");
                object.notify();
            }
        }, "t2").start();
    }
}
