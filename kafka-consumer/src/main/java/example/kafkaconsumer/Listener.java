package example.kafkaconsumer;

import example.shared.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Listener {

	@KafkaListener(topics = "test", groupId = "group-1")
	public void consume(Message message, Acknowledgment acknowledgment) {
		log.info("Consumiendo mensaje: {}", message);
		acknowledgment.acknowledge();
	}
}
