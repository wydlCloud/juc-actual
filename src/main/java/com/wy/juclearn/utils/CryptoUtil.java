package com.wy.juclearn.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created on 2018/8/9.
 * Title: Simple
 * Description:
 * Copyright: Copyright(c) 2018
 * Company:
 *
 * @author wy
 */
public class CryptoUtil {

    public static String aesBase64Encrypt(String clearText, String keyStr) {
        String str;
        try {

            SecretKeySpec localSecretKeySpec = new SecretKeySpec(keyStr.getBytes("UTF8"), "AES");
            Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            localCipher.init(Cipher.ENCRYPT_MODE, localSecretKeySpec);
            byte[] arrayOfByte1 = clearText.getBytes("UTF8");
            byte[] arrayOfByte2 = localCipher.doFinal(arrayOfByte1);
            str = Base64.getEncoder().encodeToString(arrayOfByte2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    public static String aesBase64Decrypt(String base64Str, String keyStr) {
        String str;
        try {

            SecretKeySpec localSecretKeySpec = new SecretKeySpec(keyStr.getBytes("UTF8"), "AES");
            Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            localCipher.init(Cipher.DECRYPT_MODE, localSecretKeySpec);
            byte[] arrayOfByte = localCipher.doFinal(Base64.getDecoder().decode(base64Str));
            str = new String(arrayOfByte, "UTF-8");
        } catch (Exception e) {
            return null;
        }
        return str;
    }
}
