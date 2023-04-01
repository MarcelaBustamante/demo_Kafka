package example.kafkaconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;

@Configuration
public class DeadLetterConfig {

	@Bean
	public DefaultErrorHandler errorHandler(KafkaTemplate deadLetterTemplate) {
		ExponentialBackOffWithMaxRetries backoff = new ExponentialBackOffWithMaxRetries(5);
		backoff.setInitialInterval(1000);
		backoff.setMultiplier(2);
		backoff.setMaxInterval(10000);
		DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(deadLetterTemplate);
		return new DefaultErrorHandler(recoverer, backoff);
	}
}
