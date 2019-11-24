package pro.buildmysoftware.spring.core.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncApp {

	public static void main(String[] args) {
		SpringApplication.run(AsyncApp.class, args);
	}
}
