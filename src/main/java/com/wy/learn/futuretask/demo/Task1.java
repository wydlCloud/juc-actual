package com.wy.learn.futuretask.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname Task1
 * @Description TODO
 */
// 洗水壶、烧开水、泡茶
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task1 implements Callable {
    private FutureTask<String> ft2;


    @Override
    public Object call() throws Exception {

        System.out.println("---T1洗水壶---");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("--- T1烧开水---");
        TimeUnit.SECONDS.sleep(15);
        String result = ft2.get();
        System.out.println("T1: 拿到茶叶:" + result);
        System.out.println("---T1泡茶---" + result);
        TimeUnit.SECONDS.sleep(2);
        return " 上茶:" + result;
    }
}
