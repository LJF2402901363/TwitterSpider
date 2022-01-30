package com.moyisuiying.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classname:TwitterUtil
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 11:29
 * @Version: 1.0
 **/
public class TwitterUtil {
    public static  String apiKey = null;
    public  static String apiSecretKey;
    public  static String accessToken;
    public  static String accessTokenSecret;
    private static Properties pro = new Properties();
    static {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("twitter-client-config.properties");
        try {
            pro.load(resourceAsStream);
            apiKey = pro.getProperty("apiKey");
            apiSecretKey = pro.getProperty("apiSecretKey");
            accessToken = pro.getProperty("accessToken");
            accessTokenSecret = pro.getProperty("accessTokenSecret");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Properties  getTwitterConfigPro(){
       return pro;
    }
}
