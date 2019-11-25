package pro.buildmysoftware.spring.core.aop.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public void method() {
		System.out.println("Hello");
	}
}
