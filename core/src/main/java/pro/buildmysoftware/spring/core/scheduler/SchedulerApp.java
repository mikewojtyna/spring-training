package pro.buildmysoftware.spring.core.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@SpringBootApplication
@EnableScheduling
public class SchedulerApp implements SchedulingConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApp.class, args);
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

	}
}
