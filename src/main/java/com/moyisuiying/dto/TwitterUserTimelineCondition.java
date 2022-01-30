package com.moyisuiying.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classname:TwitterUserTimelineCondition
 *    {
 *     "userName":"用户名字",
 *     "nbTweets":"获取推文的数量，可以不填",
 *     "startTime":"yyyy-MM-dd HH:mm:ss格式，可以为空不填",
 *     "endTime":"yyyy-MM-dd HH:mm:ss格式，可以为空不填",
 *     "sinceId":"数字格式，可以为空不填",
 *     "untilId":"数字格式，可以为空不填"
 *   }
 * @description: getUserTimeline的封装条件
 * @author: 陌意随影
 * @Date: 2022-01-30 12:45
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterUserTimelineCondition {
    private String userName;
    private int  nbTweets;
    private String startTime;
    private String endTime;
    private String sinceId;
    private String untilId;
}
