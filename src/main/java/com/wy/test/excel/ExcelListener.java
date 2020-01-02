package com.wy.test.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class ExcelListener extends AnalysisEventListener {

    /**
     * 导入数据超长文案
     */
    public static final String SIZE_TOO_LONG_MESSAGE = "size too long";

    /**
     * 表头不匹配
     */
    public static final String MISMATCHED_HEADERS_MESSAGE = "mismatched headers";

    /**
     * 默认2000条，可以通过构造方法设置 然后清理list ，方便内存回收
     */
    private int batchCount = 2000;

    /**
     * excel对应的实体类型，用来校验表头
     */
    private Class<?> clazz;

    public ExcelListener() {
    }

    public ExcelListener(int batchCount, Class<?> clazz) {
        this.batchCount = batchCount;
        this.clazz = clazz;
    }

    List<Object> list = new ArrayList<>();

    @Override
    public void invoke(Object data, AnalysisContext context) {
        list.add(data);
        if (list.size() > this.batchCount) {
            list.clear();
            throw new RuntimeException(SIZE_TOO_LONG_MESSAGE);
        }
    }

    /**
     * 校验表头是否完全匹配
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        // 校验表头
        Field[] fields = this.clazz.getDeclaredFields();
        if (fields.length != headMap.size()) {
            throw new RuntimeException(MISMATCHED_HEADERS_MESSAGE);
        }
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        for (Field field : fields) {
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            a.add(excelProperty.value()[0]);
        }
        for (Object obj : headMap.values()) {
            b.add((String) obj);
        }
        a.removeAll(b);
        if (!a.isEmpty()) {
            throw new RuntimeException(MISMATCHED_HEADERS_MESSAGE);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }


    public List<Object> getList() {
        return list;
    }


    /**
     * 加上存储数据库
     */
    private void saveData() {
        //doing .... 如果需要这里可以加上其他逻辑
    }
}