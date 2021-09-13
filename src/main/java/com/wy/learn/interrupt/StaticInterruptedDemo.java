package com.wy.learn.interrupt;

/**
 * @author wy
 * @company
 * @Classname StaticInterruptedDemo
 * @Description TODO
 */

public class StaticInterruptedDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
        System.out.println("111111");
        Thread.currentThread().interrupt();///----false---> true
        System.out.println("222222");
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
        // 下面输出false 的主要原因是，在上一步进行获取标记后，就重置为false了。
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
    }
}
