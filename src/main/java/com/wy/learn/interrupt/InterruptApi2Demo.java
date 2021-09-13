package com.wy.learn.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname InterruptApi2Demo
 * @Description 检验 标记置为true ，并不是直接stop线程，并不是直接停止程序
 */

public class InterruptApi2Demo {
    public static void main(String[] args) {
        m1();
    }

    public static void m1(){
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("------i: " + i);
            }
            System.out.println("t1.interrupt()调用之后02： " + Thread.currentThread().isInterrupted());
        }, "t1");
        thread.start();;

        System.out.println("t1.interrupt()调用之前,t1线程的中断标识默认值： "+thread.isInterrupted());

        try { TimeUnit.MILLISECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        //实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程

        thread.interrupt();
        //活动状态,t1线程还在执行中
        System.out.println("t1.interrupt()调用之后01： "+thread.isInterrupted());
        try { TimeUnit.MILLISECONDS.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        //非活动状态,t1线程不在执行中，已经结束执行了。
        System.out.println("t1.interrupt()调用之后03： "+thread.isInterrupted());
    }

}
