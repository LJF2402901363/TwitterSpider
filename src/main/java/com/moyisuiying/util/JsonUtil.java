package com.moyisuiying.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moyisuiying.dto.TwitterUserTimelineCondition;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classname:JsonUtil
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-30 12:28
 * @Version: 1.0
 **/
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private  static List<TwitterUserTimelineCondition> usersList = new ArrayList<>();
    static {
        //读取配置中需要监听的Twitter用户名
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("conditions.json");
        try {
            TwitterUserTimelineCondition[] users = objectMapper.readValue(resourceAsStream, TwitterUserTimelineCondition[].class);
            usersList.addAll(Arrays.asList(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TwitterUserTimelineCondition> getAllUsers(){
        return usersList;
    }

}
