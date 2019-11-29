package pro.buildmysoftware.spring.integration;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.handler.GenericHandler;

@Configuration
@IntegrationComponentScan
public class Config {

	@Bean("numberHandler")
	public GenericHandler<Integer> numberHandler() {
		return (number, headers) -> {
			System.out.println(number);
			return null;
		};
	}

	@Bean
	public EventPublisher eventPublisher(ApplicationEventPublisher springEventPublisher) {
		return event -> springEventPublisher.publishEvent(event);
	}
}
