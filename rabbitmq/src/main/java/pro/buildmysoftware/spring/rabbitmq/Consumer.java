package pro.buildmysoftware.spring.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "hello")
@Component
public class Consumer {

	private MessageCollector messageCollector;

	public Consumer(MessageCollector messageCollector) {
		this.messageCollector = messageCollector;
	}

	@RabbitHandler
	public void handle(String msg) {
		messageCollector.collectMsg(msg);
	}
}
