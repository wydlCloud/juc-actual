package com.wy.juclearn.three.locksuppert;

import java.util.concurrent.locks.LockSupport;

/**
 * Created on 2019/9/25.
 * Title: Simple
 * Description:
 * locksupport是一个非常方便实用的线程阻塞工具，它可以在线程内任意位置让线程阻塞。和Thread.suspend相比，它弥补了由于resume在前发生，导致线程无法继续执行的情况。
 * 和Object.wait()相比，它不需要先获得某个对象的锁，也不会抛出InterruptException异常。
 * LockSupport是一个非常方便实用的线程阻塞工具，类似的还有parkNanos()、parkUtil()等方法。它们实现了一个限时的等待。
 *
 * 这里的locksupport类使用类似信号量的机制，它为每一个线程准备了一个许可，如果许可可用，那么park函数会立即返回，并且消费这个许可（也就是将许可变为不可用），
 * 如果许可不可用，就会阻塞。而unpark()则使得一个许可变为可用（但是和信号量不同的是，许可不能累加，你不可能拥有超过一个许可，它永远只有一个）
 *
 * 这个特点使得：即使unpark()操作发生在park之前，它也可以使得下一次的park()操作立即返回。这也是可顺利执行不会阻塞的原因。
 *
 * 同时处理park()状态的线程不会向suspend()那样还给出一个令人费解的Runable的状态。它会非常明确的给出一个waiting状态，甚至还会标记park()引起的
 *
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class LockSupportDemo {

    public static Object u=new Object();
    static ChangeObjectThread changeObjectThread=new ChangeObjectThread("t1");
    static ChangeObjectThread changeObjectThread1=new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }
        @Override
        public void run(){
            synchronized (u){
                System.out.println("in:"+getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        changeObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread1.start();
        LockSupport.unpark(changeObjectThread);
        LockSupport.unpark(changeObjectThread1);
        changeObjectThread.join();
        changeObjectThread1.join();
    }
}
