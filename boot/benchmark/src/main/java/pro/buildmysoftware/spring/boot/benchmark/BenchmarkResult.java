package pro.buildmysoftware.spring.boot.benchmark;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

public class BenchmarkResult {

	private Duration time;
	private Exception exception;

	public BenchmarkResult(Duration time) {
		this.time = time;
	}

	public BenchmarkResult(Duration time, Exception exception) {
		this.time = time;
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "BenchmarkResult{" + "time=" + time + ", exception=" + exception + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BenchmarkResult that = (BenchmarkResult) o;
		return Objects.equals(time, that.time) && Objects
			.equals(exception, that.exception);
	}

	@Override
	public int hashCode() {
		return Objects.hash(time, exception);
	}

	public Duration getTime() {
		return time;
	}

	public Optional<Exception> getException() {
		return Optional.ofNullable(exception);
	}
}
