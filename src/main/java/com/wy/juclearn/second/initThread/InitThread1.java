package com.wy.juclearn.second.initThread;

/**
 * @author wy
 * @Classname InitThread
 * @Description 初始化线程
 * @Date 2019/9/20 12:24 上午
 * @author by wy
 */
public class InitThread1 extends Thread{


    //如果想让线程来处理点什么，就要重写run方法
    @Override
    public void run() {
        System.out.println("第一种方式：通过继承Thread来实现");
    }

    public static void main(String[] args) {
        InitThread1 initThread1=new InitThread1();
        //在这里需要注意的是 start 方法和run 方法的区别，不要用run来开启线程，它只会在当前线程中，
        // 串行执行run中的代码。只是作为一个普通方法的调用

        initThread1.start();
        //initThread1.run();

       //创建方式
        new InitThread1(){
            @Override
            public void run() {
                System.out.println("第二种方式：通过继承Thread来实现，其实和第一种相同");
            }
        }.start();

    }
}
