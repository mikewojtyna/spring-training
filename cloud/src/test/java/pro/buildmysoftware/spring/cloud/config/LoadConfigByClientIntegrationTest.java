package pro.buildmysoftware.spring.cloud.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoadConfigByClientIntegrationTest {

	@Autowired
	private GreeterClient greeterClient;

	@DisplayName("should load greeting message from config server")
	@Test
	void test() throws Exception {
		// when
		String greetings = greeterClient.greet();

		// then
		Assertions.assertThat(greetings)
			.isEqualTo("Hello World from config server!");
	}
}
