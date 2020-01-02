package com.wy.test.excel.test;

import com.wy.test.excel.DateUtils;
import com.wy.test.excel.EasyExcelUtil;
import com.wy.test.excel.ExcelException;

import java.util.Date;
import java.util.List;

/**
 * @author wy
 * @Classname Write
 * @Description TODO
 * @Date 2019/12/20 2:43 下午
 */
public class Write {
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    public <T extends CodeMessage> String write(List<T> goods, Class<T> tClass) throws ExcelException {
        String s = "导入数据_" + DateUtils.format(new Date(), yyyyMMddHHmmssSSS);
        String realPath = "/Users/wy/Downloads";
        String path = EasyExcelUtil.writeExcel(goods, s, realPath, "导入失败的数据", tClass);
        return path;
    }
}
