package com.wy.juclearn.three.semaphore;

import java.util.concurrent.*;

/**
 * Created on 2019/9/24.
 * Title: Simple
 * Description:信号量
 * 信号量为多线程协作提供了更为强大的控制方法。广义上说，信号量是对锁的扩展。无论是隐式锁synchronized还是显示锁ReentrantLock，一次只允许一个
 * 线程访问临界区资源，而信号量确可以指定多个线程，同时访问某一个资源。信号量主要提供了以下构造函数：
 * 1. public Semaphore(int permits)
 * 2.public Semaphore(int permits,boolean fair)  第二个参数可以指定是否公平
 * <p>
 * 在构造信号量对象时，必须要指定信号量的准入数，即同时能申请多少个许可。当每个线程每次只申请一个许可时，这就相当于同时有多少个线程可以访问某一临界区资源。
 * 信号量的主要逻辑方法主要有：
 * <p>
 * //尝试获得一个准入的许可。若无法获得，则线程会等待，直到有线程释放一个许可或者当前线程被中断。
 * semaphore.acquire();
 * //和acquire类，但是不响应中断。
 * semaphore.acquireUninterruptibly();
 * //尝试获得一个许可，如果成功返回true，失败返回false，它不会进行等待，立即返回。
 * semaphore.tryAcquire();
 * //和上面类似，只是等待10秒，如果没获取到准入许可，则返回false
 * semaphore.tryAcquire(10, TimeUnit.SECONDS);
 * //用于在线程访问资源结束后，释放一个许可，以使得其他等待许可的线程可以进行资源访问。
 * semaphore.release();
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class SemaphoreDemo implements Runnable {
    final static Semaphore s = new Semaphore(5);
    final static LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue();

    @Override
    public void run() {
        try {
            //申请信号量的操作
            s.acquire();
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "-------done!");
            //在离开是要记得释放信号量操作，这和是释放锁是一个道理，不然的话，后来可以访问临界区资源的线程会越来越少
            //因为并没有进行释放，这种情况的话，加一个就少一个，如果五个都没有释放，就是陷入类似于的死锁状态，其他线程不再能访问，
            //因为没有线程可以进行准入许可了。
          //  s.release();
            System.out.println("-----------------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor
                (20, 30, 0, TimeUnit.SECONDS, linkedBlockingQueue);
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(semaphoreDemo);
        }
    }
}
