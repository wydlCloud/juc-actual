package com.wy.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcelUtil
 * 基于easyExcel的开源框架，poi版本3.17
 * BeanCopy ExcelException 属于自定义数据，属于可自定义依赖
 * 简单导入导出工具类
 */
public class EasyExcelUtil {
    /**
     * 私有化构造方法
     */
    private EasyExcelUtil(){}


    /**
     * 读取 第一个Sheet的 Excel
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     * 默认最大限制读取2000条
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> rowModel) throws ExcelException, SizeTooLongException, MismatchedHeadersException {
        return readExcel(excel,rowModel,0, 2000);
    }

    /**
     * 读取 第一个Sheet的 Excel
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param batchCount excel行数限制
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> rowModel, int batchCount) throws ExcelException, SizeTooLongException, MismatchedHeadersException {
        return readExcel(excel,rowModel,0, batchCount);
    }

    /**
     * 读取某个 sheet 的 Excel
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T> List<T> readExcel(MultipartFile excel, Class<T> rowModel, int sheetNo, int batchCount) throws ExcelException, SizeTooLongException, MismatchedHeadersException {
        ExcelListener excelListener = new ExcelListener(batchCount, rowModel);
        ExcelReader reader =null;
        try {
            reader = getReader(excel,rowModel,excelListener);
            if (reader == null) {
                return new ArrayList<>();
            }
            ReadSheet readSheet = EasyExcel.readSheet(sheetNo).head(rowModel).build();
            reader.read(readSheet);
        } catch (ExcelException e) {
            if(null != reader){
                reader.finish();
            }
            throw new ExcelException("读取excel异常!");
        } catch (RuntimeException e) {
            if (ExcelListener.SIZE_TOO_LONG_MESSAGE.equals(e.getMessage())) {
                throw new SizeTooLongException(ExcelListener.SIZE_TOO_LONG_MESSAGE);
            } else if (ExcelListener.MISMATCHED_HEADERS_MESSAGE.equals(e.getMessage())) {
                throw new MismatchedHeadersException(ExcelListener.MISMATCHED_HEADERS_MESSAGE);
            }
            throw e;
        } finally {
            if(null != reader){
                reader.finish();
            }
        }
        return getExtendsBeanList(excelListener.getList(),rowModel);
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     * @param realPath  真实路径:request.getSession().getServletContext().getRealPath("/");
     * @param list      数据 list，excel数据导出的来源
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @return physicalDirPath 根据list生成的excel文件的真实物理地址
     */
    public static <T> String writeExcel(List<T> list, String fileName, String realPath, String
                                        sheetName,Class<T> classType)  throws ExcelException{
        //真实物理地址
        String  physicalDirPath = realPath + fileName + ExcelTypeEnum.XLSX.getValue();
        if(sheetName == null || "".equals(sheetName)){
            sheetName = "sheet1";
        }
        ExcelWriter writer = EasyExcel.write(physicalDirPath, classType).build();
        WriteSheet sheet = EasyExcel.writerSheet(sheetName).build();
        try {
            writer.write(list, sheet);
        } finally {
            writer.finish();
        }
        return physicalDirPath;
    }

    /**
     * 返回 ExcelReader
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static <T> ExcelReader  getReader(MultipartFile excel, Class<T>  rowModel,
                                              ExcelListener excelListener) throws ExcelException{
        String fileName = excel.getOriginalFilename();
        if (fileName == null ) {
            throw new ExcelException("文件格式错误！");
        }
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = excel.getInputStream();
            ExcelReader excelReader = EasyExcel.read(inputStream, rowModel, excelListener).build();
            return excelReader;
        } catch (IOException e) {
            //do something
        }
        return null;
    }

    /**
     * 利用BeanCopy转换list
     */
    public static <T> List<T> getExtendsBeanList(List<?> list,Class<T> typeClazz){
        return MyBeanCopy.convert(list,typeClazz);
    }
}
