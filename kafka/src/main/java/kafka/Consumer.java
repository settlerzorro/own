package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

/******************************************************
 ****** @ClassName   : Consumer.java
 ****** @author      : zhangshuai
 ****** @date        : 2018 03 14 15:50
 ****** @version     : v1.0.x
 *******************************************************/
public class Consumer {
    private static final String topic = "nokia.enms.notif_ws";

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.2:9092");//单节点，kafka多节点时候使用,逗号隔开
        props.put("group.id", "1111"); //定义消费组
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        TopicPartition topicPartition=new TopicPartition(topic,1);
        consumer.assign(Arrays.asList(topicPartition));//订阅主题

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("消费者11111offset = %d, key = %s, value = %s,partition = %s%n", record.offset(), record.key(), record.value(),record.partition());
            }
        }
    }
}