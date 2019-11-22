package pro.buildmysoftware.spring.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication
			.run(Main.class, args);
		context.getBean(Producer.class).sendMsg("hello");
		context.getBean(MessageCollector.class).getMessages()
			.forEach(msg -> System.out
				.println("Received msg: " + msg));
		context.close();
	}

	@Bean
	public Queue queue() {
		return new Queue("hello");
	}
}
