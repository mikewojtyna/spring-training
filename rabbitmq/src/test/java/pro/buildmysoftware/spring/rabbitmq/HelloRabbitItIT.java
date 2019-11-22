package pro.buildmysoftware.spring.rabbitmq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloRabbitItIT {

	@Autowired
	private Producer producer;
	@Autowired
	private MessageCollector messageCollector;

	@DisplayName("send and receive hello message")
	@Test
	void test() throws Exception {
		// when
		producer.sendMsg("hello");

		// then
		assertThat(messageCollector.getMessages())
			.containsExactlyInAnyOrder("hello");
	}
}
