package pro.buildmysoftware.spring.boot.benchmark.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.buildmysoftware.spring.boot.benchmark.Benchmark;
import pro.buildmysoftware.spring.boot.benchmark.TimeoutBenchmark;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DefaultBenchmarkStarterIntegrationTest {

	@Autowired
	private Benchmark benchmark;

	@DisplayName("load benchmark using starter")
	@Test
	void test() throws Exception {
		assertThat(benchmark).isInstanceOf(TimeoutBenchmark.class);
		assertThat(((TimeoutBenchmark) benchmark).getTimeout())
			.isEqualTo(10_000);
	}
}
