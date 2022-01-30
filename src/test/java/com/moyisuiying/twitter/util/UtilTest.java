package com.moyisuiying.twitter.util;

import com.moyisuiying.util.TwitterUtil;
import org.junit.Test;

/**
 * Classname:UtilTest
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 11:33
 * @Version: 1.0
 **/
public class UtilTest {
    @Test
    public void testUtil(){
        System.out.println(TwitterUtil.apiKey);
        System.out.println(TwitterUtil.apiSecretKey);
        System.out.println(TwitterUtil.accessToken);
        System.out.println(TwitterUtil.accessTokenSecret);
    }

}
