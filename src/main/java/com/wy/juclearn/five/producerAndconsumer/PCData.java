package com.wy.juclearn.five.producerAndconsumer;

/**
 * @author wy
 * @Classname PCData
 * @Description TODO
 * @Date 2019/10/12 6:45 下午
 */
public class PCData {
    private final int intData;

    public PCData(int d) {
        intData = d;
    }
    public PCData(String d){
        intData=Integer.valueOf(d);
    }
    public int getData(){
        return intData;
    }

    @Override
    public String toString() {
        return "data:"+intData;
    }
}
