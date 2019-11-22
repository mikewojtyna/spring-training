package pro.buildmysoftware.spring.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private RabbitTemplate rabbitTemplate;
	private Queue queue;

	public Producer(RabbitTemplate rabbitTemplate, Queue queue) {
		this.rabbitTemplate = rabbitTemplate;

		this.queue = queue;
	}

	public void sendMsg(String msg) {
		rabbitTemplate.convertAndSend(queue.getName(), msg);
	}
}
