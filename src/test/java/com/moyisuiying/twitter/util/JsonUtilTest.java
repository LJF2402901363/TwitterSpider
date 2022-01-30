package com.moyisuiying.twitter.util;

import com.moyisuiying.dto.TwitterUserTimelineCondition;
import com.moyisuiying.util.JsonUtil;
import org.junit.Test;

import java.util.List;

/**
 * Classname:JsonUtilTest
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-30 12:58
 * @Version: 1.0
 **/
public class JsonUtilTest {
    @Test
    public void testGetAllUser(){
        List<TwitterUserTimelineCondition> allUsers = JsonUtil.getAllUsers();
        allUsers.forEach(System.out::println);
    }
}
