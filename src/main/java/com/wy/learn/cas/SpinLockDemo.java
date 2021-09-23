package com.wy.learn.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wy
 * @company
 * @Classname SpinLockDemo
 * @Description 实现自旋锁
 *
 * 自旋锁的好处：不会使线程处于wating或者blocked状态  不会造成阻塞
 *
 * 通过cas来实现自旋
 * A线程先进来调用myLock方法自己持有锁5秒
 * B随后进来发现当前线程持有锁，不是null
 * 所以只能自旋进行等待
 * 直到A释放锁，B才能拿到锁
 */

public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");
        // expect 旧的预期值   需要更新的值  如果旧的预期值 和内存中的值是相等的话，则返回true  否则返回false
        // 第一次比较的话，肯定是相等的，false  随后，就直接更新为了thread
        // 另一个线程进来的话，相比较就不是null了，不相等了回false 就会一直进行自旋，直到相等为止
        while (!atomicReference.compareAndSet(null, thread)){

        };
    }

    public void  myUnLock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System. out .println(Thread. currentThread ().getName()+ " \t  myUnLock over" );
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }).start();
        // 先暂停一会，保证A线程优先于B线程先启动完成
        TimeUnit.SECONDS.sleep(1L);

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }).start();

    }
}
