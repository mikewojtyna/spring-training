package pro.buildmysoftware.spring.core.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

	private static final Logger logger = LoggerFactory
		.getLogger(LoggingAspect.class);

	@Before(
		// @formatter:off
		"execution(* pro.buildmysoftware.spring.core.aop.service.*.*(..))"
		// @formatter:on
	)
	public void logMethodCall(JoinPoint joinPoint) {
		logger.info("Calling method {}", joinPoint.getSignature()
			.getName());
	}
}
