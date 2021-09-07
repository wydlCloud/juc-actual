package com.wy.learn.futuretask;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.*;

/**
 * @author wy
 * @company
 * @Classname FatureTaskTest
 * @Description TODO
 */

public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println("come in FutureTask");
            TimeUnit.SECONDS.sleep(9);
            return "" + ThreadLocalRandom.current().nextInt(10);
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(t1.getName());
        // 10秒之后才会出来结果，还没有计算完成，提前来拿的话，就会造成阻塞，
        System.out.println("---------------------------");
        // 只要一调用get，就会对结果造成不见不散的效果，导致主线程阻塞
        String s = futureTask.get();
        System.out.println(Thread.currentThread().getName() + "\t" + s);


        // 如果超过了时间，会抛出异常，如果不进行捕获，会进行抛出java.util.concurrent.TimeoutException此异常
//        System.out.println(Thread.currentThread().getName() + "\t" + futureTask.get(1L, TimeUnit.SECONDS));

        // 如何进行计算结束呢
//        boolean cancel = futureTask.cancel(true);
//        System.out.println(cancel);
        // 判断是否已经取消
//        boolean cancelled = futureTask.isCancelled();
//        System.out.println(cancelled);
//  判断是否关闭
//        boolean done = futureTask.isDone();
//        System.out.println(done);
        // 如果取消的话，在这个位置会报错
//        String s = futureTask.get();
//        System.out.println(s);
        System.out.println(Thread.currentThread().getName() + " \t " + " run... here");
    }
}
