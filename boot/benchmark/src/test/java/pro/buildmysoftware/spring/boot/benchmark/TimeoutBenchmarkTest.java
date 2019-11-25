package pro.buildmysoftware.spring.boot.benchmark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

class TimeoutBenchmarkTest {

	// @formatter:off
	@DisplayName(
		"measure simple task"
	)
	// @formatter:on
	@Test
	void testSimple() throws Exception {
		// given
		Benchmark benchmark = new TimeoutBenchmark(Long.MAX_VALUE);

		// when
		CompletableFuture<BenchmarkResult> result = benchmark
			.run(() -> System.out.println("hello"));

		// then
		assertThat(result.get().getTime()).isNotNull();
	}

	// @formatter:off
	@DisplayName(
		"measure long task"
	)
	// @formatter:on
	@Test
	void testLong() throws Exception {
		// given
		Benchmark benchmark = new TimeoutBenchmark(Long.MAX_VALUE);

		// when
		CompletableFuture<BenchmarkResult> result = benchmark
			.run(() -> {
				try {
					Thread.sleep(2000);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			});

		// then
		assertThat(result.get().getTime())
			.isGreaterThanOrEqualTo(Duration.ofMillis(2000));
	}
}