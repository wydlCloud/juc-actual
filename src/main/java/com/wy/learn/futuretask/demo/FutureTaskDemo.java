package com.wy.learn.futuretask.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wy
 * @company
 * @Classname FutureTaskDemo
 * @Description 烧水实例
 * <p>
 * 并发编程最重要的三个核心点，  分工 同步  互斥 三个核心点
 * 写并发程序，首先要做的就是分工，所谓分工指的是如何高效地拆解任务并分配给线程。
 * <p>
 * 关于烧水这个示例
 * <p>
 * 要进行两步骤战略
 * 洗水壶--> 烧水-（在此处要依赖于拿茶叶的结果）->泡茶叶
 * <p>
 * 洗茶壶-->洗茶杯-->拿茶叶
 * <p>
 * 第一步的泡茶叶需要等待第二步的拿茶叶结束  所以在这个过程中要进行分析每个阶段的耗时情况，来合理的进行任务的拆分
 */

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new Task2());
        FutureTask<String> ft1 = new FutureTask<>(new Task1(ft2));

        Thread t1 = new Thread(ft1);
        t1.start();
        Thread t2 = new Thread(ft2);
        t2.start();

        // 等待线程 T1 执行结果
        System.out.println(ft1.get());

    }

}
