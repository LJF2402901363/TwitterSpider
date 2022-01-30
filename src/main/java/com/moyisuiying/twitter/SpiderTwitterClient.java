package com.moyisuiying.twitter;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.dto.user.User;
import com.github.redouane59.twitter.dto.user.UserV2;
import com.github.redouane59.twitter.helpers.ConverterHelper;
import com.github.redouane59.twitter.signature.TwitterCredentials;
import com.moyisuiying.dto.TweetDto;
import com.moyisuiying.util.TwitterUtil;
import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Classname:TwitterClient
 *{
 *   "apiKey": "xxx",
 *   "apiSecretKey": "xxx",
 *   "accessToken": "xxx",
 *   "accessTokenSecret": "xxx"
 * }
 * @description:推特连接客户端
 * @author: 陌意随影
 * @Date: 2022-01-29 11:05
 * @Version: 1.0
 **/
public class SpiderTwitterClient {
    public final static String API_KEY = "apiKey";
    public final static String API_SECRET_KEY = "apiSecretKey";
    public final static String ACCESS_TOKEN = "accessToken";
    public final static String ACCESS_TOKEN_SECRET = "accessTokenSecret";
    private TwitterClient twitterClient;
    private static final Map<String, String> hashMap = new HashMap<>();
    public SpiderTwitterClient(){
        //默认调用配置
        this(TwitterUtil.getTwitterConfigPro());
    }
    public SpiderTwitterClient(Properties twitterConfigPro) {
        this.twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(twitterConfigPro.getProperty(ACCESS_TOKEN))
                .accessTokenSecret(twitterConfigPro.getProperty(ACCESS_TOKEN_SECRET))
                .apiKey(twitterConfigPro.getProperty(API_KEY))
                .apiSecretKey(twitterConfigPro.getProperty(API_SECRET_KEY))
                .build());
    }

    public SpiderTwitterClient(String apiKey, String apiSecretKey, String accessToken, String accessTokenSecret) {

        this.twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(accessToken)
                .accessTokenSecret(accessTokenSecret)
                .apiKey(apiKey)
                .apiSecretKey(apiSecretKey)
                .build());
    }
    /**
     * @Description :获取用户的推文
     * @Date 12:04 2022/1/29 0029
     * @Param * @param userId 用户ID
     * @param nbTweets 获取的数量
     * @param startTime 开始时间，可以为空
     * @param endTime 结束时间，可以为空
     * @param sinceId 起始推文ID
     * @param untilId ：结束推文ID
     * @return java.util.List<com.github.redouane59.twitter.dto.tweet.Tweet>
     **/
    public List<Tweet> getUserTimeline(String userId, int nbTweets, LocalDateTime startTime, LocalDateTime endTime, String sinceId, String untilId){
        if (startTime == null){
            startTime = ConverterHelper.dayBeforeNow(1);
        }
        if (endTime == null){
            endTime  = ConverterHelper.dayBeforeNow(0);
        }
        List<Tweet> userTimeline = this.twitterClient.getUserTimeline(userId,nbTweets,startTime,endTime,sinceId,untilId);
        return userTimeline;
    }
    /**
     * @Description :获取用户的推文
     * @Date 12:04 2022/1/29 0029
     * @Param * @param userName 用户名
     * @param nbTweets 获取的数量
     * @param startTime 开始时间，可以为空
     * @param endTime 结束时间，可以为空
     * @param sinceId 起始推文ID
     * @param untilId ：结束推文ID
     * @return java.util.List<TweetDto>
     **/
    public List<TweetDto> getUserTimelineByUserName(String userName, int nbTweets, LocalDateTime startTime, LocalDateTime endTime, String sinceId, String untilId){

        List<TweetDto>  tweetDtoList = new ArrayList<>();
        String userId = hashMap.get(userName);
        if (userId == null){
            //首先通过userName获取userId
            UserV2 userByUserName = this.getUserByUserName(userName);
            if (userByUserName == null || userByUserName.getData() == null){
                return  tweetDtoList;
            }
            userId = userByUserName.getId();
            if (userId == null || "".equals(userId)){
                return tweetDtoList;
            }else{
                //将username和userId键值对缓存到 map中
                hashMap.put(userName,userId);
            }
        }
        if (startTime == null){
            startTime = ConverterHelper.dayBeforeNow(1);
        }
        if (endTime == null){
            endTime  = ConverterHelper.dayBeforeNow(0);
        }

        List<Tweet> userTimeline = this.getUserTimeline(userId,nbTweets,startTime,endTime,sinceId,untilId);
        for (Tweet tweet:userTimeline){
            tweetDtoList.add(new TweetDto(tweet,userName));
        }
        return tweetDtoList;
    }
    /**
     * @Description :通过推特ID获取推文
     * @Date 12:39 2022/1/29 0029
     * @Param * @param tweetId ：推文ID
     * @return com.github.redouane59.twitter.dto.tweet.Tweet
     **/
    public Tweet getTweetById(String tweetId){
      return   this.twitterClient.getTweet(tweetId);
    }
    /**
     * @Description :通过UserId获取User
     * @Date 12:59 2022/1/29 0029
     * @Param * @param userId ：User的ID
     * @return com.github.redouane59.twitter.dto.user.User
     **/
    public User getUserByUserId(String userId){
        User userFromUserId = this.twitterClient.getUserFromUserId(userId);
        return  userFromUserId;
    }
    /**
     * @Description :通过UserId获取User
     * @Date 12:59 2022/1/29 0029
     * @Param * @param userName ：User的name
     * @return com.github.redouane59.twitter.dto.user.User
     **/
    public UserV2 getUserByUserName(String userName){
        UserV2 userFromUserName = this.twitterClient.getUserFromUserName(userName);
        return  userFromUserName;
    }
}
