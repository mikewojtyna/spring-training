package pro.buildmysoftware.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class GreeterClient {

	private String greeting;

	public GreeterClient(@Value("${hello.message}") String greeting) {
		this.greeting = greeting;
	}

	public String greet() {
		return greeting;
	}
}
