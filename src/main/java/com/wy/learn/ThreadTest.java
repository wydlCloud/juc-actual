package com.wy.learn;


/**
 * @author wy
 * @company
 * @Classname ThreadTest
 * @Description TODO
 */

public class ThreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = Thread.currentThread().getName() + "\t 开始运行,";
            String s1 = Thread.currentThread().isDaemon() ? "守护线程" : "用户线程";
            String newS = s + s1;
            System.out.println(newS);

        }, "t1");
        // 线程的 daemon 属性为 true 表示是守护线程， false 表示是用户线程      
        t1.setDaemon(true);
        t1.start();
        //3 秒钟后主线程再运行     
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("----------main 线程运行完毕 ");
    }

}
