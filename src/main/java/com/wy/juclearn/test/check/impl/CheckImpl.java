package com.wy.juclearn.test.check.impl;

import com.wy.juclearn.test.ErrorMessage;
import com.wy.juclearn.test.check.Check;

/**
 * @author wy
 * @Classname OneImpl
 * @Description TODO
 * @Date 2019/12/18 4:26 下午
 */
public class CheckImpl implements Check {
    @Override
    public ErrorMessage one() {
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setCode(500);
        errorMessage.setMessage("hehehehe");
        return errorMessage;
    }
}
