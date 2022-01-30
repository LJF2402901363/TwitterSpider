package com.moyisuiying.kafka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moyisuiying.dto.TweetDto;
import com.moyisuiying.util.KafkaUtil;
import org.apache.kafka.clients.producer.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
/**
 * Classname:TwitterProducer
 *
 * @description:
 * @author: 陌意随影
 * @Date: 2022-01-29 13:33
 * @Version: 1.0
 **/
public class TwitterProducer {
    private Producer<String, String> producer;
    private ObjectMapper objectMapper;
    //默认的topic
    public static final String TWEET_TOPIC = "tweet_topic";
    public TwitterProducer(Properties properties) {
        this.producer = new KafkaProducer<>(properties);
        this.objectMapper = new ObjectMapper();
    }

    public TwitterProducer() {
        this(KafkaUtil.getKafkaProducerConfig());
    }
    /**
     * @Description :将TweetDto发送到kafka
     * @Date 13:56 2022/1/29 0029
     * @Param * @param tweetDto ：
     * @return void
     **/
    public void sendTwitterToKafka(TweetDto tweetDto) {
        String writeValueAsString = null;
        try {
            writeValueAsString = this.objectMapper.writeValueAsString(tweetDto);
            ProducerRecord<String,String> producerRecord = new ProducerRecord<>(TWEET_TOPIC,writeValueAsString);

            Future<RecordMetadata> send = producer.send(producerRecord, (recordMetadata, e) -> {
                long offset = recordMetadata.offset();
                System.out.println("发送推文ID为："+tweetDto.getTweetId()+"的数据到kafka成功，在kafka中的offset为：" + offset);
            });
            //刷新直接强制推送
            producer.flush();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    /**
     * @Description :将TweetDto发送到kafka
     * @Date 13:56 2022/1/29 0029
     * @Param * @param tweetDto ：
     * @return void
     **/
    public void sendTwitterToKafka(List<TweetDto> tweetDtoList)  {
       if (tweetDtoList == null){
           return;
       }
       tweetDtoList.forEach(tweetDto->{
           String writeValueAsString = null;
           try {
               writeValueAsString = this.objectMapper.writeValueAsString(tweetDto);
               ProducerRecord<String,String> producerRecord = new ProducerRecord<>(TWEET_TOPIC,writeValueAsString);
               producer.send(producerRecord, (recordMetadata, e) -> {
                   long offset = recordMetadata.offset();
                   System.out.println("发送推文ID为："+tweetDto.getTweetId()+"的数据到kafka成功，在kafka中的offset为：" + offset);
               });

           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
       });
       //将数据全部推送到kafka
       producer.flush();
    }
}
