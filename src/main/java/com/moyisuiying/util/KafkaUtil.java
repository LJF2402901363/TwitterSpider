package com.moyisuiying.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classname:KafkaUtil
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 13:34
 * @Version: 1.0
 **/
public class KafkaUtil {
    private static Properties kafka_producer_config = new Properties();
    private static Properties kafka_consumer_config = new Properties();
    static {
        InputStream producer_resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("kafka-producer.properties");
        InputStream consumer_resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("kafka-consumer.properties");

        try {
            kafka_producer_config.load(producer_resourceAsStream);
            kafka_consumer_config.load(consumer_resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Properties getKafkaProducerConfig(){
        return kafka_producer_config;
    }
    public static Properties getKafkaConsumerConfig(){
        return kafka_consumer_config;
    }
}
