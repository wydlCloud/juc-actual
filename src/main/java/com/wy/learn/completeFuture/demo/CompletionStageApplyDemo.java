package com.wy.learn.completeFuture.demo;

import java.util.concurrent.CompletableFuture;

/**
 * @author wy
 * @company
 * @Classname CompletionStageDemo
 * @Description TODO
 */

public class CompletionStageApplyDemo {

    public static void main(String[] args) {

        //首先通过 supplyAsync() 启动一个异步流程，之后是两个串行操作，整体看起来还是挺简单的。
        // 不过，虽然这是一个异步流程，但任务①②③却是串行执行的，
        // ②依赖①的执行结果，
        // ③依赖②的执行结果。
        CompletableFuture<String> ft = CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> s + "QQ")
                .thenApply(String::toUpperCase);
        System.out.println(ft.join());

        CompletableFuture<String> ft1 = CompletableFuture.supplyAsync(() -> {
            return "hello world";
        }).thenApply(s -> {
            return s+"   hhhhh";
        }).thenApply(ss -> ss.toUpperCase());
        System.out.println(ft1.join());

    }
}
