package pro.buildmysoftware.spring.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest("spring.main.allow-bean-definition-overriding=true")
public class TransformMessageInputChannelToLengthIntegrationTest {

	@Autowired
	private GreetingsSource greetingsSource;
	@Autowired
	private CollectingNumbersHandler numberHandler;
	@MockBean
	private EventPublisher eventPublisher;

	// @formatter:off
	@DisplayName(
		"given hi and hey messages, " +
		"then numbers 2 and 3 are produced"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		greetingsSource.provideGreetings("hi", "hey");

		// then
		assertThat(numberHandler.collectedNumbers)
			.containsExactly(2, 3);
		verify(eventPublisher).publish(2);
		verify(eventPublisher).publish(3);
	}

	@TestConfiguration
	public static class TestConfig {

		@Bean("numberHandler")
		public CollectingNumbersHandler testNumberHandler() {
			return new CollectingNumbersHandler();
		}
	}

	private static class CollectingNumbersHandler implements GenericHandler<Integer> {

		private List<Integer> collectedNumbers = new ArrayList<>();

		@Override
		public Object handle(Integer integer,
				     MessageHeaders messageHeaders) {
			collectedNumbers.add(integer);
			return null;
		}
	}

}
