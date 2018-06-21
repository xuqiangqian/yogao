package com.yogo.fm.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-04
 */
public class Base64Utils {
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Encoder encoder = Base64.getEncoder();

    /**
     * 编码
     *
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String text) throws UnsupportedEncodingException {
        return encoder.encodeToString(text.getBytes("UTF-8"));
    }

    public static String decode(String text) {
        return new String(decoder.decode(text));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(encode("10001"));
    }

}
