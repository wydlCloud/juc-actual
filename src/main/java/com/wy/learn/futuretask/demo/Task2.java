package com.wy.learn.futuretask.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname Task1
 * @Description TODO
 */

public class Task2 implements Callable {
    // T2Task 需要执行的任务:
// 洗茶壶、洗茶杯、拿茶叶
    @Override
    public Object call() throws Exception {
        System.out.println("---T2洗茶壶---");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("---T2洗茶杯---");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("---T2拿茶叶---");
        TimeUnit.SECONDS.sleep(2);
        return "西湖龙井";
    }
}
