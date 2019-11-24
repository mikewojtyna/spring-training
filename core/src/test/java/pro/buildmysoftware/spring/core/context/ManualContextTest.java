package pro.buildmysoftware.spring.core.context;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class ManualContextTest {

	@DisplayName("get calculator bean")
	@Test
	void test() throws Exception {
		// given
		ApplicationContext context = ManualContext.createContext();

		// when
		Calculator calculator = context.getBean(Calculator.class);

		// then
		assertThat(calculator.add(2, 3)).isEqualTo(5);
	}
}