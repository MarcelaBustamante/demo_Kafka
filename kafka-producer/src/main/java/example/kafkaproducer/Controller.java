package example.kafkaproducer;

import example.shared.Message;
import example.shared.WrongMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/producer")
public class Controller {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public Controller(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping
	public void produce(@RequestBody Message message) {
		log.info("Produciendo mensaje: {}", message);
		kafkaTemplate.send("test", message);
	}

	@PostMapping("/wrong")
	public void produceWrongMessage(@RequestBody WrongMessage message) {
		log.info("Produciendo mensaje: {}", message);
		kafkaTemplate.send("test", message);
	}
}
