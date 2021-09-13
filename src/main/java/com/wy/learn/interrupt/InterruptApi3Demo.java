package com.wy.learn.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname InterruptApi3Demo
 * @Description
 */

public class InterruptApi3Demo {

    public static void m1() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("-----isInterrupted() = true，程序结束。");
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // 如果抛出异常的话，会导致标志位重置为false ，所以就无法停止，想停止的话，就要进行再次中断
                    Thread.currentThread().interrupt();//???????  //线程的中断标志位为false,无法停下，需要再次掉interrupt()设置true
                    e.printStackTrace();
                }
                System.out.println("------hello Interrupt");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * <p> If this thread is blocked in an invocation of the {@link
         *      * Object#wait() wait()}, {@link Object#wait(long) wait(long)}, or {@link
         *      * Object#wait(long, int) wait(long, int)} methods of the {@link Object}
         *      * class, or of the {@link #join()}, {@link #join(long)}, {@link
         *      * #join(long, int)}, {@link #sleep(long)}, or {@link #sleep(long, int)},
         *      * methods of this class, then its interrupt status will be cleared and it
         *      * will receive an {@link InterruptedException}.
         *      上面这段话，就解释了为什么要进行重新打标  true
         */
        new Thread(() -> {
            t1.interrupt();//修改t1线程的中断标志位为true
        }, "t2").start();
    }

}
