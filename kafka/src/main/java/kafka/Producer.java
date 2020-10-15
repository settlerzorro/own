package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/******************************************************
 ****** @ClassName   : Producer.java
 ****** @author      : milo ^ ^
 ****** @date        : 2018 03 14 11:34
 ****** @version     : v1.0.x
 *******************************************************/
public class Producer {


    private static final String TOPIC = "nbox-telemetry-benchmark-event";
    private static final String BROKER_LIST = "135.251.96.99:9092";
    private static KafkaProducer<String,String> producer = null;

    /*
    初始化生产者
     */
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    /*
    初始化配置
     */
    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static void main(String[] args) throws InterruptedException {
        //消息实体
        ProducerRecord<String , String> record = null;
        int num = 4;
        while(true){
            String id ="5f6abe841984756664d68f2"+num;
            String b = "{\"eventType\":\"FIBER-MAIN-FAIL\",\"objectId\":\"5f154bad57c5827bb55886e6_17630208_5f154bdb57c5827bb55886fd_17040384\",\"objectType\":\"CONNECTION\",\"isCleared\":false,\"serverity\":\"CRITICAL\",\"eventTime\":1600934405316,\"data\":{\"direction\":\"ZA\",\"atNBaseline\":0.0,\"atNDetected\":26.34,\"exceeded\":26.34,\"outputPower\":12.55,\"inputPower\":-13.79,\"aend\":\"EDFA-1-4-R\",\"zend\":\"EDFA-1-2-T\",\"connId\":\"5f1fb4830b8051221a68b0c0_PORT-1-2-L_5f1fb4e20b8051221a68b0ef_PORT-1-4-L\",\"ane\":\"172.24.168.175\",\"zne\":\"172.24.168.176\"}}";
            String a = "{\"_id\": \""+id+"\""+", \t\"objectId\": \"5f154bad57c5827bb55886e6_17630208_5f154bdb57c5827bb55886fd_17040384\", \t\"objectType\": \"CONNECTION\", \t\"isCleared\": false, \t\"serverity\": \"MAJOR\", \t\"eventTime\": 1600830097885, \t\"data\": { \t\t\"direction\": \"AZ\", \t\t\"atNBaseline\": 12.0, \t\t\"atNDetected\": 20.34, \t\t\"exceeded\": 8.34, \t\t\"outputPower\": 12.4, \t\t\"inputPower\": -7.94, \t\t\"aend\": \"EDFA-1-4-T\", \t\t\"zend\": \"EDFA-1-2-R\", \t\t\"connId\": \"5f1fb4830b8051221a68b0c0_PORT-1-2-L_5f1fb4e20b8051221a68b0ef_PORT-1-4-L\", \t\t\"ane\": \"172.24.168.175\", \t\t\"zne\": \"172.24.168.176\" \t} }";
            num++;
            record = new ProducerRecord<String, String>(TOPIC, b);
            //发送消息
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null != e){
                        System.out.println("send error" + e.getMessage());
                    }else {
                        System.out.println(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                    }
                }
            });
           Thread.sleep(10000);
        }
    }
}