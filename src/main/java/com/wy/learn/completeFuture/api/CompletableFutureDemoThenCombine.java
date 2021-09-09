package com.wy.learn.completeFuture.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname CompletaFutureDemo
 * @Description
 */

public class CompletableFutureDemoThenCombine {

    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
                System.out.println("I am sleep 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2L);
                        System.out.println("I am sleep 2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "world";
                }), (s1, s2) -> s1 + s2);
        String join = cf.join();
        System.out.println(join);

    }
}
