package com.javaer.plusxposed.util.digest;


import android.util.Base64;

public class Base64Util {
    /**
     * base64 encode
     * @param string str
     * @return encode str
     */
    public static String encode(final String string) {
        byte[] bytes = Base64.encode(string.getBytes(), Base64.DEFAULT);
        return new String(bytes);
    }

    /**
     * base64 decode
     * @param string str
     * @return decode str
     */
    public static String decode(final String string){
        byte[] bytes = Base64.decode(string, Base64.DEFAULT);
        return new String(bytes);
    }
}
