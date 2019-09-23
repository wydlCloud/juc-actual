package com.wy.juclearn.second.daemon;

/**
 * Created on 2019/9/23.
 * Title: Simple
 * Description:
 * 设置守护线程必须在线程start（）之前设置，否则你会得到一个类似以下的异常IllegalThreadStateException，告诉你守护线程设置失败
 * 但是程序和线程依然可以执行，只是被当做用户线程而已。因此如果不小心忽略下面的异常信息，你就可能察觉不到这个错误。
 * <p>
 * 在这个例子中，由于t被设置为守护线程，系统中只有主线程main为用户线程，因此在main线程休眠2秒后退出时，整个程序也随之结束。但如果不把线程t设置
 * 为守护线程，main线程结束后，t线程还会不停地打印，永远不会结束。
 *
 * 总结：如果把一个线程设置为守护线程，当在一个线程中把另一个线程设置为守护线程，当前线程结束后，另一个线程也会随机结束，并不会一直运行下去
 * Copyright: Copyright(c) 2019
 * Company:
 *
 * @author wy
 */
public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            System.out.println("守护线程===,I am alive");
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("守护线程===");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DaemonT();
        thread.setDaemon(true);
        thread.start();
        //thread.setDaemon(true);

        Thread.sleep(2000);
        System.out.println("主线程结束");
    }
}
