package com.wy.learn.completeFuture.api;

import java.util.concurrent.*;

/**
 * @author wy
 * @company
 * @Classname CompletableFutureApi
 * @Description TODO
 */

public class CompletableFutureApi {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
//        Integer join = CompletableFuture.supplyAsync(() -> {
//            return 1;
//        }).thenApply(s -> {
//            return s + 2;
//        }).whenComplete((v, e) -> {
//            if (e == null) {
//                System.out.println("zoulema");
//                System.out.println(e);
//            }
//            System.out.println("zoulema---");
//            System.out.println(v);// 这个V就是上一步的返回结果
//        }).exceptionally(e -> {
//            e.printStackTrace();
//            return null;
//        }).join();
//
//        System.out.println(join);
//        m1();
        m2();
    }

    /**
     * 获取结果和触发计算
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public static void m1() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, threadPoolExecutor);
//        System.out.println(cf.get(1L, TimeUnit.SECONDS));
        TimeUnit.SECONDS.sleep(1);
        // 没有计算完成的情况下，给我一个替代结果
//        System.out.println(cf.getNow(9999));
        // 是否打断get方法立即返回括号值
        System.out.println(cf.complete(-44) + "\t" + cf.get());
    }

    public static void m2() throws InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1024");
            return 1024;
        }).thenApply(x -> {
            System.out.println("1025");
            return 1024 + 1024;
        }).thenApply(y -> {
            System.out.println("1026");
            return 1024 * 3;
        }).whenComplete((v, e) -> {
            System.out.println("*****v" + v);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        // 上述的thenApply方法减少了阻塞和空转轮询的方式，但是在get或者join的时候，还是会进行阻塞的，因为要获取异步计算的结果。
        System.out.println("-----主线程结束");
        Integer join = cf.join();
        System.out.println(join);
        int j = 0;
        for (int i = 0; i < 10000; i++) {
            j = ++i;
            System.out.println(j);
        }
        // 此处主线程先不要结束，否则completableFuture默认线程池会立刻关闭
        TimeUnit.SECONDS.sleep(20);

    }
}
