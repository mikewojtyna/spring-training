package pro.buildmysoftware.spring.cloud.eureka.client.greetings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EurekaDiscoverer implements ApplicationRunner {

	private DiscoveryClient discoveryClient;
	private String clientName;
	private RestTemplate restTemplate;

	public EurekaDiscoverer(DiscoveryClient discoveryClient, @Value("$" +
		"{spring.application.name}") String appName,
				RestTemplate restTemplate) {
		this.discoveryClient = discoveryClient;
		clientName = appName;
		this.restTemplate = restTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		while (true) {
			List<ServiceInstance> instances = discoveryClient
				.getInstances(clientName);

			if (!instances.isEmpty()) {
				System.out.println("Found instances!");
				instances.stream().forEach(i -> {
					System.out
						.printf("Instance: " + "host " + "%s, " + "instanceId: " + "%s, Uri %s\n", i
							.getHost(), i
							.getInstanceId(), i
							.getUri());

					System.out
						.printf("Calling %s service" + " to get all greetings \n", clientName);

					Greetings greetings = restTemplate
						.getForObject(String
							.format("http://%s" +
								"/api" +
								"/greetings",
								clientName),
							Greetings.class);

					System.out
						.println("Received " +
							"greetings: " + greetings);
				});
				break;
			}
			else {
				System.out
					.println("No instances found " +
						"yet");
			}
			Thread.sleep(1000);
		}
	}
}
