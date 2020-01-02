package com.wy.juclearn.test.validata.impl;

import com.wy.juclearn.test.ErrorMessage;
import com.wy.juclearn.test.check.Check;
import com.wy.juclearn.test.validata.Validata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @Classname ValidateImpl
 * @Description TODO
 * @Date 2019/12/18 4:15 下午
 */
public class ValidataImpl implements Validata {
    @Override
    public <T> List<T> validata(List<T> list, Check one) {
        List<T> list1=new ArrayList<>();
        for (T errorMessage:list){
            ErrorMessage error=(ErrorMessage) errorMessage;
            ErrorMessage errorMessage1=new ErrorMessage();
            errorMessage1.setMessage(error.getMessage());
            errorMessage1.setCode(error.getCode());
            list1.add((T) error);
           // System.out.println(errorMessage1.getCode()+"---"+errorMessage1.getMessage());
        }
        return list1;
    }
}
