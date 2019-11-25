package pro.buildmysoftware.spring.boot.benchmark.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.buildmysoftware.spring.boot.benchmark.Benchmark;
import pro.buildmysoftware.spring.boot.benchmark.TimeoutBenchmark;

@Configuration
@EnableConfigurationProperties(BenchmarkProperties.class)
public class BenchmarkAutoConfiguration {

	@Autowired
	private BenchmarkProperties benchmarkProperties;

	@Bean
	@ConditionalOnMissingBean(Benchmark.class)
	public Benchmark benchmark() {
		return new TimeoutBenchmark(benchmarkProperties.getTimeout());
	}
}
