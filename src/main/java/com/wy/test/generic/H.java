package com.wy.test.generic;

import java.util.List;

/**
 * @author wy
 * @Classname T
 * @Description TODO
 * @Date 2019/12/19 7:41 下午
 */
public interface H {
    <T extends A> String eat(List<T> model);
}
