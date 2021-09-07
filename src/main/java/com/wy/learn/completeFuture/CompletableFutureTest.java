package com.wy.learn.completeFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname CompletableFuture
 * @Description TODO
 */

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " \t " + "-----come in");
            // 暂停几秒钟线程             
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----task is over");
        });
        System.out.println(future.get());
    }

}
