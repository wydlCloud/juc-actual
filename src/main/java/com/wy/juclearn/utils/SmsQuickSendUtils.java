package com.wy.juclearn.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wy
 * @company
 * @Classname SmsQuickSendUtils
 * @Description TODO
 */

public class SmsQuickSendUtils {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        System.out.println(String.format("timestamp = %s", timestamp));
        String sign = Md5Encrypt.md5("88997676" + timestamp + "9owbhL9K9pGyj10V");
        System.out.println(String.format("sign = %s", sign));
        // 运行代码前请去掉反斜杠\编译
        Map<String, String> params = new HashMap<String, String>();
        params.put("smsFreeSignName", "光云工单系统");
        Integer encode = getEncode();
        params.put("msg", "验证码为 " + encode + ",5分钟内有效。");
        params.put("mobile", "17600699591");
        params.put("appId", "88997676");
        params.put("timestamp", timestamp);
        params.put("sign", sign);
        String result = doPost("http://dxapi2.superboss.cc/sms/sendSms", params);
        System.out.println(result);
    }

    public static String doPost(String uri, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        try {
            java.net.URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            //向post请求发送json数据
            String jsonStr = appendJson(params);
            out.write(jsonStr);
            out.flush();
            out.close();
            //取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                    System.out.println(line);
                }
            } else {
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String appendJson(Map<String, String> params) {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append("\"").append(entry.getKey()).append("\"")
                    .append(":")
                    .append("\"").append(entry.getValue()).append("\"")
                    .append(",");
        }
        String paramsJson = result.toString();
        paramsJson = paramsJson.substring(0, paramsJson.length() - 1);
        paramsJson = paramsJson + "}";
        return paramsJson;
    }

    public static Integer getEncode() {
        for (int i = 0; i <= 200; i++) {
            int intFlag = (int) (Math.random() * 1000000);

            String flag = String.valueOf(intFlag);
            if (flag.length() == 6 && flag.substring(0, 1).equals("9")) {
                System.out.println(intFlag);
            } else {
                intFlag = intFlag + 100000;
                System.out.println(intFlag);
            }
            return intFlag;
        }
        return null;
    }
}

