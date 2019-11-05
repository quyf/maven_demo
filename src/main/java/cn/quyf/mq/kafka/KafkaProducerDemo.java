package cn.quyf.mq.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerDemo extends Thread {
	private final KafkaProducer<Integer, String> producer;
	private final String topic;
	private final Boolean isAsync;

	public KafkaProducerDemo(String topic, Boolean isAsync) {
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put("client.id", "DemoProducer");
		configMap.put("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
		configMap.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		configMap.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		configMap.put("acks", "all");
		producer = new KafkaProducer<>(configMap);
		this.topic = topic;
		this.isAsync = isAsync;
	}

	public void run() {
		int messageNo = 1;
		while (true) {
			String messageStr = "Message_" + messageNo;
			long startTime = System.currentTimeMillis();
			if (isAsync) { // Send asynchronously
				producer.send(new ProducerRecord<>(topic, messageNo, messageStr),
						new DemoCallBack(startTime, messageNo, messageStr));
			} else { // Send synchronously
				try {
					producer.send(new ProducerRecord<>(topic, messageNo, messageStr)).get();
					System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			++messageNo;
			if(messageNo==100){
				break;
			}
		}
	}
}

class DemoCallBack implements Callback {

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling of request completion. This method will
     * be called when the record sent to the server has been acknowledged. Exactly one of the arguments will be
     * non-null.
     *
     * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
     *                  occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                    "), " +
                    "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}