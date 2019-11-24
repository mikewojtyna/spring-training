package pro.buildmysoftware.spring.core.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class AsyncApp extends AsyncConfigurerSupport {

	public static void main(String[] args) {
		SpringApplication.run(AsyncApp.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
		return super.getAsyncExecutor();
		// configure the executor here
		// return Executors.newSingleThreadExecutor();
	}
}
