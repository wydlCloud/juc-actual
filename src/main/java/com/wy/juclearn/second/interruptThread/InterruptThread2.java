package com.wy.juclearn.second.interruptThread;

/**
 * @author wy
 * @Classname InterruptThread2
 * @Description TODO
 * @Date 2019/9/20 10:54 上午
 */
public class InterruptThread2 {
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
                    try {
                        //sleep方法会抛出异常，已经捕获到了中断，这个时候，我们就直接可以中断线程，
                        // 但是我们并不能这么做，如果后面还有一系列的处理逻辑，可能就会导致数据不一致性，
                        // 所以在异常中设置中断标记位，来进行标记，这个整体的处理执行完再进行中断线程。
                        //sleep方法由于中断而抛出异常，此时，它会清除中断标记，如果不加处理，那么在
                        //下一次循环开始时，就无法捕获这个中断，故在异常处理中，再次设置中断标记位。
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupt when sleep......");
                        //这是中断状态(设置中断标示位)
                        Thread.currentThread().interrupt();
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
