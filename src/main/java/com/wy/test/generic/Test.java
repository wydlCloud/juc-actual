package com.wy.test.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @Classname Test
 * @Description TODO
 * @Date 2019/12/19 7:44 下午
 */
public class Test {

    public static void main(String[] args) {
        B b = new B();
        b.setCount(100);
        b.setHeight(100);
        b.setCode(200);
        b.setMessage("成功");
        B b1=new B();
        b1.setCount(500);
        b1.setHeight(500);
        b1.setCode(500);
        b1.setMessage("失败");
        List<B> bs=new ArrayList<>();
        bs.add(b);
        bs.add(b1);
        H h=new Impl();
        String eat = h.eat(bs);
        System.out.println(eat);

    }
}
