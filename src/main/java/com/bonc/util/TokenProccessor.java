package com.bonc.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Author:ChenZhiXiang
 * @Description: 生成Token的工具类
 * @Date:Created in 10:45 2018/9/6
 * @Modified By:
 */
public class TokenProccessor {

    private TokenProccessor(){};

    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 生成Token
     * @return
     */
    public static String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}