package pro.buildmysoftware.spring.core.scheduler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SchedulerIntegrationTest {

	@MockBean
	private ScheduledActionHandler handlerStrategy;

	// @formatter:off
	@DisplayName("run handler at least 3 times when running for 4 seconds")
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		Thread.sleep(4000);

		// then
		verify(handlerStrategy, atLeast(3)).handle();
	}
}
