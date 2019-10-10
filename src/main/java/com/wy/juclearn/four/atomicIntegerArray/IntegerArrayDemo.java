package com.wy.juclearn.four.atomicIntegerArray;

import com.wy.juclearn.util.JsonUtil;

/**
 * @author wy
 * @Classname IntArrayDemo
 * @Description TODO
 * @Date 2019/10/10 5:42 下午
 */
public class IntegerArrayDemo {
    static Integer[] arr = new Integer[10];

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                arr[k % arr.length] = arr[k % arr.length] + 1;
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
        System.out.println(JsonUtil.toJson(arr));
    }
}
