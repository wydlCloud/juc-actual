package com.wy.juclearn.three.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2019/9/24.
 * Title: Simple
 * Description:
 * 非常实用的多线程控制工具类。Count down在英文意为倒计数
 * latch为门闩的意思。
 * 简单的称之为倒计数器。
 * 在这里，门闩的意思是：把门锁起来，不让里面的线程跑出来。因此，这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。
 * <p>
 * 对于倒计时器，一种典型的场景就是火箭发射。在火箭发射前，为了保证万无一失，往往还要进行各项设备仪器的检查。只有等待所有的检查完毕后，引擎才能点火。
 * 这种场景就适合使用countdownlatch。它可以使得点火线程等待所有检查线程全部完工后再进行执行。
 * <p>
 * countdownlatch的构造函数接收一个整数作为参数，即当前这个计数器的计数个数。
 * countdownlatch(int count)
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class CountDownLatchDemo implements Runnable {
    private static final CountDownLatch countDownLatch = new CountDownLatch(10);
    private static final CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(100);
            System.out.println("check complete");
            //这一步属于是线程执行结束一个，就要调用countdown来进行-1操作
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(countDownLatchDemo);
        }
        //等待检查，调用线程（主线程）等待等待在countDownLatch上，等检查线程执行结束才会进行调用线程（主线程）继续执行
        countDownLatch.await();
        //检查完成
        System.out.println("pass");
        executorService.shutdown();

    }

}
