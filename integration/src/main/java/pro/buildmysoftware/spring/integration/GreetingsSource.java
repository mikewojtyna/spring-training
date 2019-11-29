package pro.buildmysoftware.spring.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface GreetingsSource {

	@Gateway(requestChannel = "messagesInputChannel")
	void provideGreetings(String... greetings);
}
