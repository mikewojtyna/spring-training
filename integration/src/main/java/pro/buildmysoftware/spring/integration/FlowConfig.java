package pro.buildmysoftware.spring.integration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.GenericHandler;

import java.time.Duration;
import java.util.List;

@Configuration
public class FlowConfig {

	@Bean
	public IntegrationFlow helloFlow() {
		return IntegrationFlows
			.from(() -> List.of("hi", "hello", "welcome"), s -> s
				.poller(Pollers
					.fixedRate(Duration.ofSeconds(1))))
			.log().get();
	}

	@Bean
	public IntegrationFlow channelFlow(@Qualifier("numberHandler") GenericHandler<Integer> numberHandler, EventPublisher eventPublisher) {
		return IntegrationFlows
			// @formatter:off
			.from("messagesInputChannel")
			.split()
			.transform(number -> number.toString().length())
			.handle((msg, headers) -> {
				numberHandler.handle((int) msg, headers);
				eventPublisher.publish(msg);
				return null;
			})
			.get();
			// @formatter:on
	}
}
