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
 * <p>
 * 小结：
 * 利用多线程可以快速将一些串行的任务并行化，并发编程，充分利用多核CPU 或者多CPU多核心的优势，把性能提升起来。
 * 从而提高性能；如果任务之间有依赖关系，
 * 比如当前任务依赖前一个任务的执行结果，这种问题基本上都可以用 Future 来解决。
 * 在分析这种问题的过程中，建议你用有向图描述一下任务之间的依赖关系，同时将线程的分工也做好，
 * 类似于烧水泡茶最优分工方案那幅图。对照图来写代码，好处是更形象，且不易出错。
 * <p>
 * <p>
 * 但是其实下面的实现其实也是有问题的，因为在get的时候，如果依赖的另一个线程还没有完成就会产生阻塞，处于waiting状态，
 * 如果使用轮询，isDone获取任务执行状态，其实也会浪费CPU的资源，因为future没有提供完成通知的功能，只能手动去获取，这也是future的不足
 * <p>
 * 而且T1 必须依赖T2 的结果，如果T2 非常耗时，T1在这个时候也就会进行阻塞，
 * 非要说性能有没有提升的话，整体性能肯定会得到提升，因为并发执行，但是T1 和 T2的依赖阻塞等待的话，确不能得到有效处理，因为业务需要，必须要这么去做
 * <p>
 * <p>
 * 要说合理的实现的话，就不能使得T1进行阻塞，因为这个时候，可能并不放弃执行权，因为唤醒的时候，还要去执行
 * sleep 会让出CPU 执行时间且强制上下文切换，而wait 则不一定，wait 后可能还是有机会重新竞争到锁继续执行的,如果被唤醒还会进行激烈的竞争
 *
 * 如果高并发情况下，任务耗时，导致大量的阻塞，导致wait，并且还不让出cpu，其实，对于cpu的负载是比较高的，如果严重的话，可能会导致服务异常，引发重大线上故障
 *
 * 所以说，在使用future的时候，要根据自己的场景来进行，因为get必然会阻塞，轮询空转，并不是非常好的处理方法，所以这个时候可以采用完成通知的方式得到结果，防止阻塞。
 *
 *
 */

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new Task2());
        FutureTask<String> ft1 = new FutureTask<>(new Task1(ft2));

        Thread t1 = new Thread(ft1);
        t1.start();
        Thread t2 = new Thread(ft2);
        t2.start();


        // 在这里可以做一些其他的事情
        // 等待线程 T1 执行结果
        System.out.println(ft1.get());

    }

}
