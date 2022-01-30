package com.moyisuiying.kafka;
import com.moyisuiying.util.KafkaUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
/**
 * Classname:TwitterConsumer
 *
 * @description: kafka消息消费者
 * @author: 陌意随影
 * @Date: 2022-01-29 14:04
 * @Version: 1.0
 **/
public class TwitterConsumer {
    KafkaConsumer<String, String> kafkaConsumer ;
    public TwitterConsumer(Properties properties) {
        this.kafkaConsumer = new KafkaConsumer<>(properties);
    }

    public TwitterConsumer() {
        this(KafkaUtil.getKafkaConsumerConfig());
    }

    public void received(){
        kafkaConsumer.subscribe(Arrays.asList(TwitterProducer.TWEET_TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println("=====================>");
            }
        }

    }

    public static void main(String[] args) {
        //启动TwitterConsumer
        TwitterConsumer twitterConsumer = new TwitterConsumer();
        twitterConsumer.received();
    }
}
