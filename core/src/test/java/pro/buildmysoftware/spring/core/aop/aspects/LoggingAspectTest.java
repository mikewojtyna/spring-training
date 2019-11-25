package pro.buildmysoftware.spring.core.aop.aspects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.buildmysoftware.spring.core.aop.service.HelloService;

@SpringBootTest
class LoggingAspectTest {

	@Autowired
	private HelloService helloService;

	@DisplayName("should log using aspect")
	@Test
	void test() throws Exception {
		// when
		helloService.method();

		// then
		// should log
	}

	@DisplayName("aop not working example")
	@Test
	void test2() throws Exception {
		// given
		HelloService helloService = new HelloService();

		// when
		helloService.method();

		// then
		// not logged
	}
}