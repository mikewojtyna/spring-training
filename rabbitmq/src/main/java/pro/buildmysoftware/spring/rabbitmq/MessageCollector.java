package pro.buildmysoftware.spring.rabbitmq;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

@Component
public class MessageCollector {

	private Collection<String> messages;
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public MessageCollector() {
		messages = new ArrayList<>();
	}

	public Collection<String> getMessages() throws InterruptedException {
		countDownLatch.await();
		return messages;
	}

	void collectMsg(String msg) {
		countDownLatch.countDown();
		messages.add(msg);
	}
}
