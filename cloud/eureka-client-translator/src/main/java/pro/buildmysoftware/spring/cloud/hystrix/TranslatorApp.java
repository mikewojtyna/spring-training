package pro.buildmysoftware.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class TranslatorApp {

	public static void main(String[] args) throws InterruptedException {
		try (ConfigurableApplicationContext context = SpringApplication
			.run(TranslatorApp.class, args)) {

			TranslatorService translatorService = context
				.getBean(TranslatorService.class);
			while (true) {
				String translation = translatorService
					.translate();
				System.out
					.println("Translation: " + translation);
				if (!translation
					.equals(TranslatorService.DEFAULT_MSG)) {
					break;
				}
				Thread.sleep(1000);
			}
		}
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
