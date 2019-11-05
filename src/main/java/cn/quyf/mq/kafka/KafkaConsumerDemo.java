package cn.quyf.mq.kafka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import kafka.utils.ShutdownableThread;

public class KafkaConsumerDemo extends ShutdownableThread {

	private final KafkaConsumer<Integer, String> consumer;
	private final String topic;

	public KafkaConsumerDemo(String topic) {
		super("KafkaConsumerExample", false);
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
		configMap.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		configMap.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		configMap.put("enable.auto.commit", true);
		configMap.put("group.id", "consumer_quyf");
		configMap.put("auto.commit.interval.ms", 1000);
		configMap.put("session.timeout.ms", 30000);
		consumer = new KafkaConsumer<>(configMap);
		this.topic = topic;
	}

	@Override
	public void doWork() {
		consumer.subscribe(Arrays.asList(this.topic));
		ConsumerRecords<Integer, String> records = consumer.poll(1000);
		for (ConsumerRecord<Integer, String> record : records) {
			System.out.println(
					"Received message: (" +record.partition() +", "+ record.key() + ", " + record.value() + ") at offset " + record.offset());
		}
	}
}
