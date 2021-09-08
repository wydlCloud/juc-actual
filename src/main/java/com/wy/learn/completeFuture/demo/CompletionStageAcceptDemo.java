package com.wy.learn.completeFuture.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname CompletionStageDemo
 * @Description TODO
 */

public class CompletionStageAcceptDemo {
    private static final String ACCEPT = "ACCEPT";

    public static void main(String[] args) throws InterruptedException {

      // 也可以thenApply和thenAccept组合使用，根据具体的业务场景

        CompletableFuture<Void> ft = CompletableFuture.supplyAsync(() -> {
            return "hello world";
        }).thenAccept(s -> System.out.println(s))
                .thenAccept(ss -> {
                    // 在此可以继续处理业务，但是入参是空的，因为上面的步骤是不能用的，而且外部的参数也传不进来，比如上面定义的静态变量
                    System.out.println("123");
                });


        CompletableFuture<String> ft1 = CompletableFuture.supplyAsync(() -> {
            return "hello world";
        }).thenAccept(s -> System.out.println(s))
                .thenApply(ss -> {
                    // 在此可以继续处理业务，但是入参是空的，因为上面的步骤是不能用的，而且外部的参数也传不进来，比如上面定义的静态变量
                    System.out.println("---------"+ss);
                    // 但是在此处可以处理业务，可以有返回值
                    return "jhahahha";
                });
        System.out.println(ft1.join());
        TimeUnit.SECONDS.sleep(100L);


    }
}
