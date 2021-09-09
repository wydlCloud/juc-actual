package com.wy.learn.completeFuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname CompletableFutureApplyToEitherDemo
 * @Description
 */

public class CompletableFutureApplyToEitherDemo {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom(5, 10);
            System.out.println("f1------"+t);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.valueOf(t);
        });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    int t = getRandom(10, 20);
                    System.out.println("f2------"+t);
                    try {
                        TimeUnit.SECONDS.sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return String.valueOf(t);
                });
        CompletableFuture<String> f3 = f1.applyToEither(f2, s -> {
            return s;
        });
        System.out.println(f3.join());
        TimeUnit.SECONDS.sleep(100L);
    }


    private static int getRandom(int a, int b) {
        if (a > b || a < 0) {
            return -1;
        }
        // 下面两种形式等价
        // return a + (int) (new Random().nextDouble() * (b - a + 1));
        return a + (int) (Math.random() * (b - a + 1));
    }
}
