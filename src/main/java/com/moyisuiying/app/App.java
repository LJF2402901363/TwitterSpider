package com.moyisuiying.app;

import com.moyisuiying.spider.TwitterConsumerTask;
import com.moyisuiying.spider.TwitterSpiderTask;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        //定时任务线程池
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        //每1分钟执行一次TwitterSpiderTask任务,始爬取数据并发送到kafka中去
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new TwitterSpiderTask(),0,1, TimeUnit.MINUTES);
        //普通任务，不会周期性执行，只会执行一次。
        // 连接kafka，开始消费数据
        scheduledThreadPoolExecutor.execute(new TwitterConsumerTask());

    }
}
