package com.wy.test.excel;

/**
 * excel条数过大异常
 *
 * @author zhigang.xu
 */
public class SizeTooLongException extends Exception {
    public SizeTooLongException(String message) {
        super(message);
    }
}
