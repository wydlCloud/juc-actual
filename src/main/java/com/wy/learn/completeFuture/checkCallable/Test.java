package com.wy.learn.completeFuture.checkCallable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname Test
 * @Description TODO
 */

public class Test {
    public static void main(String[] args) throws InterruptedException {
        FutureTask futureTask = new FutureTask<>(new TaskTest1());
        Thread thread = new Thread(futureTask);
        thread.start();
        TimeUnit.SECONDS.sleep(5L);
        System.out.println("---主线程结束----");
    }
}
