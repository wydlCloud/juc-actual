package com.wy.juclearn.second.stopThread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wy
 * @Classname StopAThreadUnsafe
 * @Description 一般来说，线程执行完成之后就会结束，无需手动关闭，但是凡事也都有例外。一些服务端的后台
 * 线程可能会常驻系统，他们通常不会正常终结。比如他们的执行体本身就是一个大大的无穷循环用于提供某些服务。
 * <p>
 * 那么如何正常的关闭一个线程呢？查阅jdk，提供了stop方法，如果使用stop方法，就可以立即将一个线程终止
 * 非常方便。但是stop这个方法标记为废弃了。
 * 之所以废弃，是因为stop方法太过去暴力，强行把执行到一般的线程终止，并且会释放锁，可能会引起一些数据不一致的情况
 * @Date 2019/9/20 9:50 上午
 */
public class StopAThreadUnsafe {
    public static User user = new User();

    @Getter
    @Setter
    @AllArgsConstructor
    public static class User {
        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
        }

        @Override
        public String toString() {
            return "user [ id=" + id + ",===" + "name=" + name + "]";
        }
    }


    public static class ChangeObjectThread implements Runnable {
        private boolean stop = false;

        public void stop() {
            stop = true;
        }

        @Override
        public void run() {
            while (true) {
                if (stop){
                    System.out.println("stop Thread ......");
                    break;
                }
                synchronized (user) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println(user.toString());
                    }else {
                        System.out.println(user.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ReadObjectThread()).start();
        //这类问题，不好排查，因为并不会报错
        while (true) {
            ChangeObjectThread changeObjectThread = new ChangeObjectThread();
            Thread thread = new Thread(changeObjectThread);
            thread.start();
            Thread.sleep(150);
            //通过这种方式来终止线程可以避免数据不一致的问题
          //  changeObjectThread.stop();
           // thread.stop();
        }
    }
}
