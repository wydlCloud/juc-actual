package com.wy.learn.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @company
 * @Classname CasDemo
 * @Description TODO
 */

public class CasDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);
atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(5,1024)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2048)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(1024,2048)+"\t"+atomicInteger.get());
    }
}
