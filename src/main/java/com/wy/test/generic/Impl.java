package com.wy.test.generic;

import java.util.List;

/**
 * @author wy
 * @Classname M
 * @Description TODO
 * @Date 2019/12/19 7:46 下午
 */
public class Impl implements H {


    @Override
    public <T extends A> String eat(List<T> list) {
        for (T t : list) {
            System.out.println(t.getCode());
            System.out.println(t.getMessage());
        }
        return "hello world";
    }
}
