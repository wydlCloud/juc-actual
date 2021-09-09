package com.wy.learn.completeFuture.checkCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname TaskTest1
 * @Description TODO
 */

public class TaskTest1 implements Callable {
    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(10L);
        System.out.println("HHHHHHHHHHHHHHHHH");
        return "hello";
    }
}
