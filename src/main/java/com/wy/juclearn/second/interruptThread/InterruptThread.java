package com.wy.juclearn.second.interruptThread;

/**
 * @author wy
 * @Classname InterruptThread
 * @Description 中断线程
 * 线程中断是一种重要的线程协作机制。
 * 从表面上理解，中断就是让目标线程停止执行的意思，实际上并不是完全如此。stop方法停止线程有很大的问题存在
 * 并且使用了一套自有的机制完善线程退出的功能。那么在jdk中提供了更强大的支持，那就是线程中断。
 * 严格来讲：线程中断并不会使得线程立即退出，而是给线程发送一个通知，告知目标线程，有人希望你退出了，
 * 至于目标线程接到通知后如何处理，则完全由目标线程自行决定。这点很重要，如果中断后，线程立即无条件退出，
 * 我们就会遇到和stop方法的老问题。
 * 与线程中断有关的，有三个方法，这三个方法看起来很像，所以可能会引起混淆和误用，一定要注意
 * Thread thread = new Thread();
 * thread.interrupt();//中断线程
 * if (thread.isInterrupted()){}//判断是否中断
 * Thread.interrupted();//判断是否中断，并清除当前中断状态
 * @Date 2019/9/20 10:32 上午
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //中断线程的手段和stop方法很相似，判断当前线程是否被中断（也就是检查中断标志位）
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("exit thread ......");
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        //通知目标线程要中断了，设置中断标志位
        thread.interrupt();
    }
}
