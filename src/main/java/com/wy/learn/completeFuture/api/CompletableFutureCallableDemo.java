package com.wy.learn.completeFuture.api;

import java.util.concurrent.*;

/**
 * @author wy
 * @company
 * @Classname CompletableFutureCallableDemo
 * @Description TODO
 */

public class CompletableFutureCallableDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        },threadPoolExecutor).thenApply(x->{
            System.out.println(x+"world");
            return x+"world";
        });
        TimeUnit.SECONDS.sleep(5L);
        System.out.println("主线程结束");
    }
}
