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
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
        m6();
        m7();

    }


    public static void m7(){
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f+2;
        }).thenApply(f -> {
            return f+3;
        }).thenAccept(r -> System.out.println(r));


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(resultA -> {}).join());


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(resultA -> resultA + " resultB").join());
    }
    /**
     * 对计算速度进行选用 也就是or的聚合，只要有一个线程完成，就可以完成下面的任务，也就是先执行完的先触发
     * applyToEither
     */
    public static void m6() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10L);
                System.out.println("t1"+"异步线程执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },threadPoolExecutor).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
                System.out.println("t2异步线程执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        }), r -> {
            return r;
        });

        Integer join = integerCompletableFuture.join();
        System.out.println(join);
        System.out.println("main 线程结束 end");
    }

    /**
     * and 汇聚运算  还可以有其他的写法，就是分开写，或者看泡茶的那个实例
     */
    public static void m5() {
        CompletableFuture<Integer> thenCombine = CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 20;
        }), (r1, r2) -> {
            return r1 + r2;
        });
        Integer join = thenCombine.join();
        System.out.println(join);

    }

    /**
     * thenAppay是使用异步线程的那个线程来进行回调以及执行，不会用新的线程来去做。
     * 异步结果回调处理，减少future的阻塞和轮询空转cpu，执行获取结构后，会进行回调方法
     */
    public static void m4() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Integer join = CompletableFuture.supplyAsync(() -> {
            return 1;
        },threadPoolExecutor).thenApply(s -> {
            return s + 2;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("zoulema");
                System.out.println(e);
            }
            System.out.println("zoulema---");
            System.out.println(v);// 这个V就是上一步的返回结果
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        }).join();

        System.out.println(join);
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

    // 对结果进行回调，有入参进行消费，并且有返回值
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

    // 对计算结果进行处理，并且有返回值，有异常给出来
    public static void m3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<Integer> m3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, threadPoolExecutor).handle((h, e) -> {
            System.out.println("-----" + 2);
            return h + 2;
        }).handle((h, e) -> {
            System.out.println("-------" + 3);
            return h + 3;
        }).handle((h, e) -> {
            System.out.println("--------" + 4);
            return h + 4;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("------result" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
//        Integer join = m3.join();
//        System.out.println(join);
        System.out.println("主线程结束，异步线程就运行你自己的完吧，我不需要，但是需要使用自定义线程池," +
                "如果不自定义，就会导致默认线程池会伴随着线程结束而释放，不管执行是否结束与否");
    }
}
