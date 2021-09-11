package com.wy.learn.threadPool;

import java.util.concurrent.*;

/**
 * @author wy
 * @company
 * @Classname ThradPoolExecutorDemo
 * @Description TODO
 */

public class ThradPoolExecutorDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i=0;i<49;i++){
            FutureTask<String> futureTask = new FutureTask<>(new Demo());
            threadPoolExecutor.execute(futureTask);
        }

    }
}
