package com.wy.juclearn.four.atomicIntegerArray;

import com.wy.juclearn.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author wy
 * @Classname AtomicIntegerArrayDemo
 * @Description 数组无锁方式
 * @Date 2019/10/10 5:07 下午
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                //将第k % array.length()下标的元素加1
                array.getAndIncrement(k % array.length());
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int k = 0; k < 10; k++) {
            threads[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            threads[k].start();
        }

        for (int k = 0; k < 10; k++) {
            threads[k].join();
        }
        System.out.println(array);
    }



}
