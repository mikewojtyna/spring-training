package pro.buildmysoftware.spring.core.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ManualContext {

	public static ApplicationContext createContext() {
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext();
		context.registerBean("lambdaCalculator", Calculator.class,
			() -> (x, y) -> x + y);
		context.refresh();
		return context;
	}
}
