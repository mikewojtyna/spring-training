package pro.buildmysoftware.spring.boot.benchmark.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pro.buildmysoftware.spring.boot.benchmark.Benchmark;
import pro.buildmysoftware.spring.boot.benchmark.BenchmarkResult;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OverrideBenchmarkStarterIntegrationTest {

	@Autowired
	private Benchmark benchmark;

	@DisplayName("load benchmark using starter")
	@Test
	void test() throws Exception {
		assertThat(benchmark).isInstanceOf(CustomBenchmark.class);
	}

	@TestConfiguration
	public static class BenchmarkTestConfiguration {

		@Bean
		public Benchmark benchmark() {
			return new CustomBenchmark();
		}
	}

	private static class CustomBenchmark implements Benchmark {

		@Override
		public CompletableFuture<BenchmarkResult> run(Runnable runnable) {
			return null;
		}
	}
}
