package com.wy.juclearn.three.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created on 2019/9/25.
 * Title: Simple
 * Description:
 * CyclicBarrier是另外一种多线程并发控制实用工具。和countdownlatch非常类似，它也可以实现线程间的计数等待，但它的功能比countdownlatch更加复杂并且强大
 * <p>
 * CyclicBarrier可以理解为循环栅栏。栅栏是一个障碍物。这里主要是阻止线程继续执行，要求线程在栅栏处等待。前面cyclic意为循环，也就是说这个计数器可以反复使用。
 * 比如：假设我们将计数器设置为10，那么凑齐第一批10个线程后，计数器就会归零，然后接着凑齐下一批10个线程，这就是循环栅栏的含义。
 * <p>
 * cyclicbarier的使用场景也很丰富。比如司机下达命令，要求10个士兵一起去完成一项任务。这个时候，就会要求10个士兵先集合报道，接着，一起雄赳赳气昂昂的去执行任务。
 * 当10个士兵把自己手头的任务都执行完成了，那么司令对外宣布，任务完成。
 * <p>
 * 比countdownlatch的是略微强大一些，cyclicbarrier可以接收一个参数作为barrierAction。所谓barrierAction就是当计数器一次计数完成后，系统会执行的动作。
 * 构造函数，其中parties表示计数总数，也就是参与的线程总数。
 * <p>
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class CyclicBarrierDemo {
    public static class Solider implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Solider(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":任务完成！");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：[士兵" + N + "个,任务完成!]");
            } else {
                System.out.println("司令：[士兵" + N + "个,集合完毕!]");
                flag = true;
            }
        }
    }


    public static void main(String[] args) {
        final int N=10;
        Thread[] allSoldier=new Thread[N];
        boolean flag=false;
        CyclicBarrier cyclicBarrier=new CyclicBarrier(N,new BarrierRun(flag,N));
        System.out.println("集合队伍：整装出发---");
        for (int i=0;i<N;i++){
            System.out.println("士兵"+i+"报道！");
            allSoldier[i]=new Thread(new Solider(("士兵"+i),cyclicBarrier));
            allSoldier[i].start();
        }
    }
}
