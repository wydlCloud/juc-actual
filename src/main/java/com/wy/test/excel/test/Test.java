package com.wy.test.excel.test;

import com.wy.test.excel.ExcelException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @Classname Test
 * @Description TODO
 * @Date 2019/12/20 11:33 上午
 */
public class Test {

    public static void main(String[] args) throws ExcelException {
        List<Goods> goods=new ArrayList<>();
        Goods goods1=new Goods();
        goods1.setCode(200);
        goods1.setItemName("椎主题");
        goods1.setMessage("新增成功");
        goods1.setStyleCatelogFirstName("一个类目");
        goods1.setStyleId("方式美滋滋");

        Goods goods2=new Goods();
        goods2.setCode(300);
        goods2.setItemName("椎主题111");
        goods2.setMessage("更新成功");
        goods2.setStyleCatelogFirstName("2个类目");
        goods2.setStyleId("方式美滋滋hahaha");


        Goods goods3=new Goods();
        goods3.setCode(500);
        goods3.setItemName("椎主题123123");
        goods3.setMessage("导入失败");
        goods3.setStyleCatelogFirstName("失败类目");
        goods3.setStyleId("方式美滋滋hahhaha");
        goods.add(goods1);
        goods.add(goods2);
        goods.add(goods3);

        Write write=new Write();
        write.write(goods, Goods.class);
    }
}
