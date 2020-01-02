package com.wy.juclearn.test.validata;

import com.wy.juclearn.test.check.Check;

import java.util.List;

/**
 * @author wy
 * @Classname Validate
 * @Description TODO
 * @Date 2019/12/18 3:55 下午
 */
public interface Validata {

    public <T> List<T> validata(List<T> list, Check t);


}
