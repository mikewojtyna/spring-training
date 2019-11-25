package pro.buildmysoftware.spring.core.profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("production")
// equivalent to
//@TestPropertySources(@TestPropertySource(properties = "spring.profiles" +
//	".active=production"))
public class ProductionProfileIntegrationTest {

	@Autowired
	private MessageBean messageBean;

	// @formatter:off
	@DisplayName("should load production config")
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		String msg = messageBean.msg();

		// then
		assertThat(msg).isEqualTo("Production message");
	}
}
