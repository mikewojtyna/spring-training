package pro.buildmysoftware.spring.boot.benchmark;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TimeoutBenchmark implements Benchmark {

	private long timeout;

	public TimeoutBenchmark(long timeout) {
		this.timeout = timeout;
	}

	public long getTimeout() {
		return timeout;
	}

	@Override
	public CompletableFuture<BenchmarkResult> run(Runnable runnable) {
		return CompletableFuture.supplyAsync(() -> {
			long start = System.currentTimeMillis();
			runnable.run();
			long end = System.currentTimeMillis();
			return end - start;
		}).thenApply(time -> new BenchmarkResult(Duration
			.of(time, ChronoUnit.MILLIS)))
			.orTimeout(timeout, TimeUnit.MILLISECONDS);
	}
}
