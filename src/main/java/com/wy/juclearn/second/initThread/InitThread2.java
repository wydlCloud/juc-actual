package com.wy.juclearn.second.initThread;

/**
 * @author wy
 * @Classname InitThread2
 * @Description 实现runable接口来创建线程
 * @Date 2019/9/20 12:38 上午
 */
public class InitThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("实现runable接口来实现线程任务");
    }

    public static void main(String[] args) {
        //Thread(Runnable target) 构造函数，本质是调用的接口的run方法，不需要重写thread的run方法了
        Thread thread = new Thread(new InitThread2());
        thread.start();
//这种方式需要是实现runable接口的方式
        new Thread(() -> {
            System.out.println("使用jdk8新特性来实现线程初始化");
        }).start();
    }
}
