package com.wy.juclearn.four.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @Classname AtomicIntegerDemo
 * @Description TODO
 * @Date 2019/10/10 3:18 下午
 */
public class AtomicIntegerDemo {
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                //会使用cas操作进行加1，同时也会返回当前值，执行程序输出是10000，说明是线程安全的，如果不是线程安全的，这里应该小于10000
                //使用atomicInteger比使用锁可以获取更好的性能，因为锁会导致线程的挂起从用户态转为内核态是需要消耗时间的，所以性能会好一些。
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads=new Thread[10];
        for (int k=0;k<10;k++){
            threads[k]=new Thread(new AddThread());
        }
        for (int k=0;k<10;k++){
            threads[k].start();
        }

        for (int k=0;k<10;k++){
            threads[k].join();
        }
        System.out.println(atomicInteger);
    }
}
