package com.example.test.encode;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Cipher;

public class CHelper {

    private static final char[] f3850a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String m5834c() {
        try {
            byte[] bArr = new byte[8];
            new SecureRandom().nextBytes(bArr);
            return m5956a(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static String m5956a(byte[] bArr) {
        if (bArr != null) {
            return m5957a(bArr, 0, bArr.length);
        }
        throw new NullPointerException("bytes is null");
    }

    public static String m5957a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        } else if (i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        } else {
            char[] cArr = new char[(i2 * 2)];
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                byte b = (byte)(bArr[i4 + i] & 255);
                int i5 = i3 + 1;
                cArr[i3] = f3850a[b >> 4];
                i3 = i5 + 1;
                cArr[i5] = f3850a[b & 15];
            }
            return new String(cArr, 0, i2 * 2);
        }
    }

    /* renamed from: a */
    public static String m4669a(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return Base64.encodeToString(instance.doFinal(str.getBytes("utf-8")), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m4670a(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(1, secretKeySpec);
            return Base64.encodeToString(instance.doFinal(bArr), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static String decode(String source, String key) {
        if (TextUtils.isEmpty(source)) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(source, 0);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return new String(instance.doFinal(decode));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m4667a() {
        String c = m5834c();
        if (c == null || c.length() != 16) {
            return null;
        }
        return c;
    }

    /* renamed from: a */
    public static String m4668a(String str) {
        if (str == null || str.length() != 16) {
            return null;
        }
        return str.concat(str).substring(8, 24);
    }


    public static void test(final List<String> fileNames, final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String file :
                        fileNames) {
                    try {
                        InputStream inputStream = activity.getAssets().open(file);

                        StringBuilder builder = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        for (String line; (line = reader.readLine()) != null; ) {
                            builder.append(line);
                        }
                        String app_logKey = "a0497c2b26294048";
                        String se = decode(builder.toString(), app_logKey);
                        Log.d("chao", file + " is " + se);
                    } catch (IOException e) {

                    }
                }

            }
        }).start();
    }



}
