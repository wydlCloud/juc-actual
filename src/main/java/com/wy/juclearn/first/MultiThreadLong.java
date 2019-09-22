package com.wy.juclearn.first;

/**
 * @Classname MultiThreadLong
 * @Description 使用32位的会出现问题，64位并无问题出现
 * 64位和32位的区别
 * 32位的系统中long型数据的读和写都不是原子性的，多线程之间相互干扰了。
 * 64位的系统解决了这个问题。
 * @Date 2019/9/19 9:51 下午
 * @Created by wy
 */
public class MultiThreadLong {
    public static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {
        @Override
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.t;
                if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) {
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }

}
