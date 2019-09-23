package com.wy.juclearn.second.priority;

/**
 * Created on 2019/9/23.
 * Title: Simple
 * Description:
 * Java中的线程可以有自己的优先级，优先级高的线程在竞争资源时候会更有优势，更可能抢占资源，当然，这只是一个概率问题
 * 由于线程的优先级调度和底层操作系统有密切的关系，在各个平台上表现不一，并且这种优先级产生的后果也可能不容易预测，
 * 并不能很精准控制，比如一个优先级的线程可能一直抢占不到资源，从而始终无法运行，而产生饥饿。因此，在要求严格的长河，还是需要
 * 自己解决线程调度问题。
 * <p>
 * <p>
 * <p>
 * 使用1-10表示线程的优先级，一般可以使用内置的三个静态标量表示：
 * 数字越大则优先级越高，但有效范围在1-10之间，高优先级的线程可以倾向于更快的完成任务
 * * The minimum priority that a thread can have.
 * <p>
 * public final static int MIN_PRIORITY=1;
 * <p>
 * /**
 * The default priority that is assigned to a thread.
 * <p>
 * public final static int NORM_PRIORITY=5;
 * <p>
 * <p>
 * * The maximum priority that a thread can have.
 * <p>
 * public final static int MAX_PRIORITY=10;
 * <p>
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class PriorityDemo {

    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                //如果不加锁的话，现在的多核cpu，对线程添加优先级，两个线程可以同时执行，不需要抢占资源，各自执行，视情况而定。（个人理解）
                //如果加速的话，就需要去抢占这把锁，这种情况下，线程的优先级越高，竞争到的几率就大，所以正常情况下效率是高的。
                // 线程优先级也主要是在锁这一块可以进行设定和使用的
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 1000000000) {
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 1000000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HightPriority();
        Thread low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        high.start();
        low.start();
    }
}
