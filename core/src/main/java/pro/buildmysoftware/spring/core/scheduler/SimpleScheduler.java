package pro.buildmysoftware.spring.core.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleScheduler {

	private ScheduledActionHandler handlingStrategy;

	public SimpleScheduler(ScheduledActionHandler handlingStrategy) {
		this.handlingStrategy = handlingStrategy;
	}

	@Scheduled(fixedDelay = 1000)
	void run() {
		handlingStrategy.handle();
	}
}
