package pro.buildmysoftware.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class GreeterClient {

	@Value("${hello.message}")
	private String greeting;

	public String greet() {
		return greeting;
	}
}
