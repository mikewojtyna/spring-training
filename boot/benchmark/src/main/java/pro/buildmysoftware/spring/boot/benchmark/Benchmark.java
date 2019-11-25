package pro.buildmysoftware.spring.boot.benchmark;

import java.util.concurrent.CompletableFuture;

public interface Benchmark {

	CompletableFuture<BenchmarkResult> run(Runnable runnable);
}
