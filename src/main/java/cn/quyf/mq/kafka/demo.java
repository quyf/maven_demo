package cn.quyf.mq.kafka;

/**
 * https://github.com/apache/kafka/tree/trunk/examples
 * @author quyf
 * @date 2017年12月20日
 */
public class demo {

	public static void main(String[] args) {
		KafkaProducerDemo producerThread = new KafkaProducerDemo("quyf_p3", false);
		producerThread.start();

		KafkaConsumerDemo consumerThread = new KafkaConsumerDemo("quyf_p3");
		consumerThread.start();
	}
}
