package com.moyisuiying.spider;

import com.github.redouane59.twitter.dto.user.UserV2;
import com.github.redouane59.twitter.helpers.ConverterHelper;
import com.moyisuiying.dto.TweetDto;
import com.moyisuiying.dto.TwitterUserTimelineCondition;
import com.moyisuiying.kafka.TwitterProducer;
import com.moyisuiying.twitter.SpiderTwitterClient;
import com.moyisuiying.util.JsonUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classname:TwitterSpiderTask
 *
 * @description: 推特爬虫，将要指定爬取的Twitter用户名的推文获取并发送到kafka中去
 * @author: 陌意随影
 * @Date: 2022-01-30 12:33
 * @Version: 1.0
 **/
public class TwitterSpiderTask implements Runnable {
    private TwitterProducer twitterProducer;
    private SpiderTwitterClient spiderTwitterClient;
    private List<TwitterUserTimelineCondition> usersList;
    public TwitterSpiderTask(){
        this.twitterProducer = new TwitterProducer();
        this.spiderTwitterClient = new SpiderTwitterClient();
        this.usersList = JsonUtil.getAllUsers();
    }

    @Override
    public void run() {
        if (this.usersList == null || this.usersList.size() == 0){
            return;
        }
        this.usersList.forEach(userTimelineCondition->{
            String username = userTimelineCondition.getUserName();
            int nbTweets = userTimelineCondition.getNbTweets();
            String startTimeStr = userTimelineCondition.getStartTime();
            LocalDateTime startTime = ConverterHelper.getDateFromTwitterDateV2(startTimeStr);
            String endTimeStr = userTimelineCondition.getEndTime();
            LocalDateTime endTime = ConverterHelper.getDateFromTwitterDateV2(endTimeStr);
            String sinceId = userTimelineCondition.getSinceId();
            String untilId = userTimelineCondition.getUntilId();
            //从Twitter中爬取用户推文
            List<TweetDto> userTimelineByUserName = this.spiderTwitterClient.getUserTimelineByUserName(username,nbTweets,startTime,endTime,sinceId,untilId);
            //将爬取到的数据发送到kafka保存
            twitterProducer.sendTwitterToKafka(userTimelineByUserName);
        });
    }
}
