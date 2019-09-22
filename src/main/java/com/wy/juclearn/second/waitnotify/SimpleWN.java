package com.wy.juclearn.second.waitnotify;

/**
 * @author wy
 * @Classname SimpleWN
 * @Description 等待和唤醒示例
 * 重点重点重点：
 * <p>
 *
 * wait和sleep的区别
 * wait除了可以被唤醒外，而且还会释放锁
 * 但是sleep并不会释放锁
 * @Date 2019/9/20 11:09 上午
 */
public class SimpleWN {
    final static Object o = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (o) {
                System.out.println(System.currentTimeMillis() + ":t1 start......");
                try {
                    System.out.println(System.currentTimeMillis() + ":t1 thread wait for object");
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end......");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (o) {
                System.out.println(System.currentTimeMillis() + ":t2 thread notify for object");
                o.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end......");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new T1();
        Thread thread1 = new T2();
        thread.start();
        thread1.start();
    }
}
