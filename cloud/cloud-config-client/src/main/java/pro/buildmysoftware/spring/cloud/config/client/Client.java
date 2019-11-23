package pro.buildmysoftware.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Client {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication
			.run(Client.class, args);
		String greet = context.getBean(GreeterClient.class).greet();

		System.out.println("Greeting: " + greet);
	}
}
