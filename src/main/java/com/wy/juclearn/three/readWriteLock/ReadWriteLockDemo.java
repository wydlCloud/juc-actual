package com.wy.juclearn.three.readWriteLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created on 2019/9/24.
 * Title: Simple
 * Description:
 * ReadWriteLock是jdk5中提供的读写分离锁。读写分离可以有效地帮助减少锁竞争，以提升系统性能。用锁分离的机制来提升性能非常容易理解
 * 比如a1，a2，a3进行写操作，b1, b2, b3进行读操作，如果使用显示锁synchronized和ReentrantLock，理论上所有的读之间，读写之间，写写之间
 * 收拾串行操作。当b1进行读取时，b2 b3，需要进行等待锁。由于读操作并不对数据的完成性造成破坏，这种等待显然不合理。因此，读写锁就产生了所用。
 * 在这种情况下，读写锁允许多个线程进行同时读，b1，b2，b3可以真正并行。但是考虑到数据完整性，写写操作和读写操作还是需要相互等待和持有锁。
 * 总结：
 * 读读：读读之间非阻塞
 * 读写：读阻塞写，写也会阻塞读
 * 写写：写写阻塞
 * <p>
 * 下面这个例子可以体现出涉及读写锁的优势和涉及到的问题
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            //   lock.lock(); //模拟读操作
            readLock.lock();
            Thread.sleep(1000);  //读操作的耗时越多，读写锁的优势越明显
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            writeLock.lock(); //模拟写操作
            // lock.lock(); //模拟读操作
            Thread.sleep(1000);  //读操作的耗时越多，读写锁的优势越明显
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        Runnable read = new Runnable() {
            @Override
            public void run() {
                try {

                    readWriteLockDemo.handleRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable write = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(read).start();
        }
        for (int j = 0; j < 20; j++) {
            new Thread(write).start();
        }

    }


}
