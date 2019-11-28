package pro.buildmysoftware.spring.cloud.eureka.client.greetings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
public class GreetingsController {

	@GetMapping
	public Greetings greetings() {
		return new Greetings("hello, hi and welcome");
	}
}
