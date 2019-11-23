package pro.buildmysoftware.spring.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class GreeterClient {

	@Value("${hello-app.message:Default greetings!}")
	private String greeting;

	public String greet() {
		return greeting;
	}
}
