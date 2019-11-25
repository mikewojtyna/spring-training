package pro.buildmysoftware.spring.core.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public interface Pointcuts {

	// @formatter:off
	@Pointcut(
		"execution(* pro.buildmysoftware.spring.core.aop.service.*.*(..))"
	)
	// @formatter:on
	static void servicePointcut() {

	}
}
