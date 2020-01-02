package com.wy.test.excel.test;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author wy
 * @Classname Goods
 * @Description TODO
 * @Date 2019/12/20 11:32 上午
 */
public class Goods extends CodeMessage {

    @ExcelProperty(index = 0, value = "数据状态")
    private String message;
    /**
     * 商品款式编码
     */
    @ExcelProperty(index = 1, value = "款式编码")
    private String styleId;
    /**
     * 商品名称
     */
    @ExcelProperty(index = 2, value = "商品名称")
    private String itemName;
    /**
     * 一级类目
     */
    @ExcelProperty(index = 3, value = "一级类目")
    private String styleCatelogFirstName;

    @ExcelIgnore
    private Integer code;



    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStyleCatelogFirstName() {
        return styleCatelogFirstName;
    }

    public void setStyleCatelogFirstName(String styleCatelogFirstName) {
        this.styleCatelogFirstName = styleCatelogFirstName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
