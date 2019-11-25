package pro.buildmysoftware.spring.core.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultConfig {

	@Bean
	public MessageBean messageBean() {
		return () -> "Default message";
	}
}
