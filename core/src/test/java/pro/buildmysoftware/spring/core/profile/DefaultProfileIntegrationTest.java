package pro.buildmysoftware.spring.core.profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("default")
//@TestPropertySources(@TestPropertySource(properties = "spring.profiles" +
//	".active=default"))
public class DefaultProfileIntegrationTest {

	@Autowired
	private MessageBean messageBean;

	// @formatter:off
	@DisplayName("should load default config")
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		String msg = messageBean.msg();

		// then
		assertThat(msg).isEqualTo("Default message");
	}
}
