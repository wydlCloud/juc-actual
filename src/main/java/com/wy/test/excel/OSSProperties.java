package com.wy.test.excel;

import org.springframework.stereotype.Component;

/**
 * Created by zengli on 2018/7/25.
 */

/**
 endpoint = http://oss-cn-hangzhou.aliyuncs.com
 seconds = 60
 accessKey=TgoXyoHO4eMP5Bs6
 secretKey=2fpiEl2Ui9EdYfpKsO97EOyLGgsXEM
 *
 */
@Component
//@ConfigurationProperties("mamahao.oss")
public class OSSProperties {

    private static String      accessKey;

    private static String      secretKey;

    private static String      endpoint;

    private static int         seconds;


    public static String getAccessKey() {
        return accessKey;
    }

    public static void setAccessKey(String accessKey) {
        OSSProperties.accessKey = accessKey;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secretKey) {
        OSSProperties.secretKey = secretKey;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        OSSProperties.endpoint = endpoint;
    }

    public static int getSeconds() {
        return seconds;
    }

    public static void setSeconds(int seconds) {
        OSSProperties.seconds = seconds;
    }
}
