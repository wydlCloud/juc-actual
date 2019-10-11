package com.wy.juclearn.four.atomicIntegerFieldUpdater;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author wy
 * @Classname AtomicIntegerFieldUpdaterDemo
 * @Description 让普通变量也享受原子操作
 * 有时候，由于初期考虑不周，或者后期的需求变化，一些普通变量可能也会有线程安全的需求。如果改动不大，我们可以简单地修改程序中每一个使用或者读取这个变量的地方。
 * 但显然，这样并不符合设计中的一条重要的原则--开闭原则。也就是系统对功能的增加应该是开放的，而对修改应该是相对保守的。而且，如果系统里使用这个变量的地方特别多，
 * 一个一个修改也是一件令人厌烦的事情（况且很多场景下可能只是只读的，并无线程安全的强烈要求，完全可以保持原样）
 *
 * 如果存在这样的困扰，可以通过原子包里还有一个实用的工具类atomicintegerFieldUpdater。它可以让你在不改动或者很少改动原有代码的基础上，让普通的变量也享受cas操作带来的线程安全性，
 * 这样就可以修改极少的代码，来获得线程安全的保证。
 *
 * 1.updater只能修改它可见范围内的变量。因为updater使用反射得到的这个变量。如果变量不可见，就会出错。比如如果score申明为private，就是不行的，因为使用private不可见，发现不了。
 * 2.为了确保变量被正确的读取，它必须是volatile类型的。如果我们原有代码中未申明这个类型，那么简单地申明一下就行，这不会引起申明问题。
 * 3.由于cas操作会通过对象实例中的偏移量直接赋值，因此不支持static字段。
 *
 * @Date 2019/10/11 9:50 上午
 */
public class AtomicIntegerFieldUpdaterDemo {

    public  static  class  Candidate{
        int id;
        volatile int score;
    }
    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater=AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");
    //检查updater是否工作正确
    public static AtomicInteger allScore=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu=new Candidate();
        Thread[] threads=new Thread[10000];
        for (int i=0;i<10000;i++){
            threads[i]=new Thread(){
                @Override
                public void run() {
                    if (Math.random()>0.4){
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            threads[i].start();
        }
        for (int i=0;i<10000;i++){
            threads[i].join();
        }
        System.out.println("score="+stu.score);
        System.out.println("allScore="+allScore);
    }
}
