package pro.buildmysoftware.spring.cloud.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TranslatorService {

	public static final String DEFAULT_MSG = "DEFAULT_MSG";
	private final String greetingsClient;
	private RestTemplate restTemplate;

	public TranslatorService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		greetingsClient = "eureka-client-greetings";
	}

	@HystrixCommand(fallbackMethod = "defaultMsg")
	public String translate() {
		Greetings greetings = restTemplate.getForObject(String.format(
			// @formatter:off
				"http://%s/api/greetings",
				// @formatter:on
			greetingsClient), Greetings.class);
		String msg = greetings.getMsg();
		if (msg.contains("hello")) {
			msg = "Cześć!";
		}
		return msg;
	}

	private String defaultMsg() {
		return DEFAULT_MSG;
	}
}
