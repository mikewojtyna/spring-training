package pro.buildmysoftware.spring.boot.benchmark.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pro.buildmysoftware.spring.boot.benchmark.Benchmark;
import pro.buildmysoftware.spring.boot.benchmark.TimeoutBenchmark;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "benchmark.timeout=999999")
public class OverrideDefaultTimeoutPropertyIntegrationTest {

	@Autowired
	private Benchmark benchmark;

	@DisplayName("override timeout property")
	@Test
	void test() throws Exception {
		assertThat(((TimeoutBenchmark) benchmark).getTimeout())
			.isEqualTo(999_999);
	}
}
