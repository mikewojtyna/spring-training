package pro.buildmysoftware.spring.core.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class ProductionConfig {

	@Bean
	public MessageBean method() {
		return () -> "Production message";
	}
}
