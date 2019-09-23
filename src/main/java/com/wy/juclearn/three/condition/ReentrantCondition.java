package com.wy.juclearn.three.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2019/9/23.
 * Title: Simple
 * Description:
 * condition和wait 和 notify一样，当使用线程Condition.await时候，要求线程持有相关的重入锁，在condition.await调用后，这个线程会释放这把锁。
 * 同样的是，在condition.signal方法调用时，也要求线程先获得相关的锁。在signal方法调用后，系统会从当前的condition对象的等待队列中唤醒一个线程，一旦线程被唤醒，它会重新尝试获得与之绑定的重入锁
 * 谦让给被唤醒的线程，让他可以继续执行。
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class ReentrantCondition implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();
    //lock生成一个与之绑定的COndition对象。
    public static Condition reentrantCondition = reentrantLock.newCondition();

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            //要求线程在condition对象上进行等待。
            reentrantCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁");
            reentrantLock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantCondition r1 = new ReentrantCondition();
        Thread thread = new Thread(r1);
        thread.start();
        Thread.sleep(2000);
        //通知r1继续执行
        reentrantLock.lock();
        //告知线程可以继续执行了。
        reentrantCondition.signal();
        //如果不释放锁，虽然r1线程被唤醒，但是它无法重新获得锁，因此它也无法真正的继续执行。
        reentrantLock.unlock();
        System.out.println("主线程结束");
    }
}
