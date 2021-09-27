package com.wy.test;

import com.alibaba.fastjson.JSON;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname Demo
 * @Description TODO
 */

public class Demo {
    public static Map<String, WeakReference<User>> weakReferenceHashMap = new HashMap<>();
    public static Map<String, User> map = new HashMap<>();

    public static void m2() {
        WeakReference<User> zhangsan = new WeakReference<>(new User("zhangsan", 12));
        weakReferenceHashMap.put("zhangsan",zhangsan);
        System.out.println("gc before user: " + zhangsan);
        System.out.println("gc before map: " + weakReferenceHashMap);

//        zhangsan = null;
        //手动挡的方式开启Gc回收。
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc after user: " + zhangsan);
        System.out.println("gc after map: " + JSON.toJSONString(weakReferenceHashMap));
        WeakReference<User> zhangsan1 = weakReferenceHashMap.get("zhangsan");
        User user = zhangsan1.get();
        System.out.println(user);
    }

    public static void m1() throws InterruptedException {
        User user = new User("lisi", 12);
        map.put("12", user);
        System.out.println("gc before user: " + user);
        System.out.println("gc before map: " + map);

        user = null;
        System.gc();//手动挡的方式开启Gc回收。
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc after user: " + user);
        System.out.println("gc after map: " + map);


    }

    public static void main(String[] args) throws InterruptedException {
//        m1();
        m2();
        ;
    }
}

