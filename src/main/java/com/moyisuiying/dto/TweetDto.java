package com.moyisuiying.dto;

import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.helpers.ConverterHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

import java.io.Serializable;

/**
 * Classname:TweetDto
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 13:49
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
public class TweetDto implements Serializable {
    private String tweetId;
    private String userId;
    private String userName;
    private String text;
    private String createAt;
    public TweetDto(Tweet tweet, String userName){
        if (tweet == null) {
            return;
        }
        this.tweetId = tweet.getId();
        this.userId = tweet.getAuthorId();
        this.text = tweet.getText();
        this.userName = userName;
        this.createAt = ConverterHelper.getStringFromDateV2(tweet.getCreatedAt());
    }
}
