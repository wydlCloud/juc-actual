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

        //首先通过 supplyAsync() 启动一个异步流程，之后是两个串行操作，整体看起来还是挺简单的。
        // 不过，虽然这是一个异步流程，但任务①②③却是串行执行的，
        // ②依赖①的执行结果，
        // ③依赖②的执行结果。

        CompletableFuture<Void> ft = CompletableFuture.supplyAsync(() -> {
            return "hello world";
        }).thenAccept(s -> System.out.println(s))
                .thenAccept(ss -> {
                    // 在此可以继续处理业务，但是入参是空的，因为上面的步骤是不能用的，而且外部的参数也传不进来，比如上面定义的静态变量
                    System.out.println("123");
                });
        TimeUnit.SECONDS.sleep(100L);


    }
}
