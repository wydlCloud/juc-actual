package com.wy.learn.futuretask;

import java.util.concurrent.*;

/**
 * @author wy
 * @company
 * @Classname FatureTaskTest
 * @Description TODO
 */

public class FutureTaskTest1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println("come in FutureTask");
            TimeUnit.SECONDS.sleep(9);
            return "" + ThreadLocalRandom.current().nextInt(10);
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(t1.getName());
        while (true) {
            if (futureTask.isDone()) {
                String s = futureTask.get();
                System.out.println(Thread.currentThread().getName() + "\t" + s);
            }
        }
    }
}
