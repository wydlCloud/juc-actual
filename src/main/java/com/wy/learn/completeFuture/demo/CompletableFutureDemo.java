package com.wy.learn.completeFuture.demo;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname CompletaFutureDemo
 * @Description * 关于烧水这个示例
 * * <p>
 * * 要进行两步骤战略
 * * 洗水壶--> 烧水
 * * <p>
 * * 洗茶壶-->洗茶杯-->拿茶叶
 * <p>
 * step3
 * ->泡茶叶(t1,t2 都执行完成(在此处要依赖于拿茶叶的结果）)
 * <p>
 * 无需手工维护线程，没有繁琐的手工维护线程的工作，给任务分配线程的工作也不需要我们关注；
 * 语义更清晰，例如 f3 = f1.thenCombine(f2, ()->{}) 能够清晰地表述“
 * 任务 3 要等待任务 1 和任务 2 都完成后才能开始”；
 */

public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<Void> ft1 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("T1洗茶壶");
                TimeUnit.SECONDS.sleep(1L);
                System.out.println("T1烧开水");
                TimeUnit.SECONDS.sleep(15L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<String> ft2 = CompletableFuture.supplyAsync(() -> {
//            洗茶壶-->洗茶杯-->拿茶叶
            try {
                System.out.println("T2洗茶壶");
                TimeUnit.SECONDS.sleep(1L);
                System.out.println("T2洗茶杯子");
                TimeUnit.SECONDS.sleep(3L);
                System.out.println("T2拿茶叶");
                TimeUnit.SECONDS.sleep(2L);
                return "T2" + "西湖龙井";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "西湖龙井";
        });

        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 =
                ft1.thenCombine(ft2, (__, tf) -> {
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });
        // 等待任务 3 执行结果
        System.out.println(f3.join());
    }
}
