package com.moyisuiying.spider;

import com.moyisuiying.kafka.TwitterConsumer;

/**
 * Classname:TwitterConsumerTask
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-30 13:02
 * @Version: 1.0
 **/
public class TwitterConsumerTask implements Runnable{
    //启动TwitterConsumer
   private TwitterConsumer twitterConsumer;

    public TwitterConsumerTask() {
        this.twitterConsumer = new TwitterConsumer();
    }

    @Override
    public void run() {
        //开始消费保存在kafka的推文数据
        twitterConsumer.received();
    }
}
