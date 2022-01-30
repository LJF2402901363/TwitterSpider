package com.moyisuiying.twitter.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.helpers.ConverterHelper;
import com.moyisuiying.dto.TweetDto;
import com.moyisuiying.kafka.TwitterProducer;
import com.moyisuiying.twitter.SpiderTwitterClient;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Classname:TwitterProducerTest
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 14:24
 * @Version: 1.0
 **/
public class TwitterProducerTest {
    private SpiderTwitterClient spiderTwitterClient = new SpiderTwitterClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private TwitterProducer twitterProducer = new TwitterProducer();
    @Test
    public void testGetUSerTimeLine(){
        LocalDateTime startTimie = ConverterHelper.dayBeforeNow(3);
        LocalDateTime endTime = ConverterHelper.dayBeforeNow(1);
        List<TweetDto>  tweetDtoList = spiderTwitterClient.getUserTimelineByUserName("the_x2y2",20,startTimie,endTime,null,null);
        twitterProducer.sendTwitterToKafka(tweetDtoList);
    }
    @Test
    public void testKafkaProducer(){
        TweetDto tweetDto = new TweetDto();
        tweetDto.setTweetId("12433");
        tweetDto.setText("你好呀");
        tweetDto.setUserName("张三");
        TweetDto tweetDto1 = new TweetDto();
        tweetDto1.setTweetId("33");
        tweetDto1.setText("你好呀");
        tweetDto1.setUserName("李四");
        twitterProducer.sendTwitterToKafka(Arrays.asList(tweetDto,tweetDto1));
    }
}
