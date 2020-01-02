package com.wy.juclearn.test;

import com.wy.juclearn.test.check.Check;
import com.wy.juclearn.test.check.impl.CheckImpl;
import com.wy.juclearn.test.validata.Validata;
import com.wy.juclearn.test.validata.impl.ValidataImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @Classname Test
 * @Description TODO
 * @Date 2019/12/18 4:31 下午
 */
public class Test {

    public static void main(String[] args) {
        List<ErrorMessage> errorMessages=new ArrayList<>();
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setMessage("服务器异常，请稍后再试");
        errorMessage.setCode(502);
        ErrorMessage errorMessage1=new ErrorMessage();
        errorMessage1.setCode(200);
        errorMessage1.setMessage("请求成功");
        errorMessages.add(errorMessage);
        errorMessages.add(errorMessage1);
        Validata validate=new ValidataImpl();
        Check one=new CheckImpl();
        List<ErrorMessage> validata = validate.validata(errorMessages, one);
        for (ErrorMessage errorMessage2:validata){
            System.out.println(errorMessage2.getCode()+"---"+errorMessage2.getMessage());
        }
    }
}
