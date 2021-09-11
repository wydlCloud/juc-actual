package com.wy.learn.threadPool;

import java.util.concurrent.Callable;

/**
 * @author wy
 * @company
 * @Classname Demo
 * @Description TODO
 */

public class Demo implements Callable {

    @Override
    public Object call() throws Exception {
        for (; ; ) {
            System.out.println("任务开始执行");
        }
    }
}
