package com.moyisuiying.app;

import com.moyisuiying.spider.TwitterConsumerTask;
import com.moyisuiying.spider.TwitterSpiderTask;

/**
 * Classname:App
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-30 12:31
 * @Version: 1.0
 **/
public class App {
    public static void main(String[] args) {
    //开始爬取数据并发送到kafka中去
    new Thread(new TwitterSpiderTask()).start();
    //开始消费数据
        new Thread(new TwitterConsumerTask()).start();
    }
}
