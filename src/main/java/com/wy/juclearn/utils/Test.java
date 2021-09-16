package com.wy.juclearn.utils;

/**
 * @author wy
 * @company
 * @Classname Test
 * @Description TODO
 */

public class Test {
    public static void main(String[] args) {
        String raycloud = CryptoUtil.aesBase64Encrypt("12345678", "raycloudraylocud");
        System.out.println(raycloud);
        String raycloud1 = CryptoUtil.aesBase64Decrypt("OoBqXBYyTxYEqzFYPQS3Uw==", "raycloudraylocud");
        System.out.println(raycloud1);
    }
}
