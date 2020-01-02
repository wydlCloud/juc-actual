package com.wy.test.excel;

/**
 * 表头不匹配
 *
 * @author zhigang.xu
 */
public class MismatchedHeadersException extends Exception {
    public MismatchedHeadersException(String message) {
        super(message);
    }
}
