package com.wy.learn.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wy
 * @company
 * @Classname AtomicInterruptDemo
 * @Description 使用原子类中断线程
 */

public class AtomicInterruptDemo {

    public static void main(String[] args) {
        m1();
    }
    static AtomicBoolean atomicBoolean=new AtomicBoolean(false);

    public static void m1(){
        new Thread(()->{
            while (true){
                if (atomicBoolean.get()){
                    System.out.println("-----atomicBoolean.get() = true，程序结束。");
                    break;
                }

            }
            System.out.println("------hello atomicBoolean");
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            atomicBoolean.set(true);
        }).start();

    }

}
