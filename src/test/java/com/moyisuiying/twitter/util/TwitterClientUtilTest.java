package com.moyisuiying.twitter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.dto.user.User;
import com.github.redouane59.twitter.dto.user.UserV2;
import com.github.redouane59.twitter.helpers.ConverterHelper;
import com.moyisuiying.dto.TweetDto;
import com.moyisuiying.twitter.SpiderTwitterClient;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Classname:TwitterClientUtilTest
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 11:22
 * @Version: 1.0
 **/

public class TwitterClientUtilTest {
    private SpiderTwitterClient spiderTwitterClient = new SpiderTwitterClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
 public void testGetTweetId(){
        Tweet tweetById = spiderTwitterClient.getTweetById("1487118695411613697");
        System.out.println(tweetById.getAuthorId());
        System.out.println(tweetById.getText());
    }
    @Test
    public void testGetUSerTimeLine(){
        LocalDateTime startTimie = ConverterHelper.dayBeforeNow(3);
        LocalDateTime endTime = ConverterHelper.dayBeforeNow(1);
        List<Tweet> userTimeline = spiderTwitterClient.getUserTimeline("1478187602465665024",20,startTimie,endTime,null,null);

        userTimeline.forEach(e->{
            if (e.getText().contains("Discord")){
                System.out.println(e.getCreatedAt());
                System.out.println(e.getText());
            }

        });
    }
    @Test
    public void testGetUSerTimeLineByUserName(){
        List<TweetDto> userTimeline = spiderTwitterClient.getUserTimelineByUserName("acx15402415124548521542",10,null,null,null,null);

        userTimeline.forEach(e->{
            System.out.println(e);

        });
    }
    @Test
    public void testGetUserByUserName(){
        UserV2 userV2 = spiderTwitterClient.getUserByUserName("the_x2y2");
        System.out.println(userV2.getId());
        System.out.println(userV2.getName());
        System.out.println(userV2.getTweetCount());
        System.out.println(userV2.getUrl());
    }

    @Test
    public void testGetUserById(){
        User userV2 = spiderTwitterClient.getUserByUserId("1478187602465665024");
        System.out.println(userV2.getId());
        System.out.println(userV2.getName());
        System.out.println(userV2.getTweetCount());
        System.out.println(userV2.getUrl());
    }

}
