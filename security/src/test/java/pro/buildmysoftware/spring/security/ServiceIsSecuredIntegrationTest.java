package pro.buildmysoftware.spring.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceIsSecuredIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	// @formatter:off
	@DisplayName("given no user, when GET on /secured, then unauthorized")
	// @formatter:on
	@Test
	void test0() throws Exception {
		// when
		mockMvc.perform(MockMvcRequestBuilders.get("/secured"))

			// then
			.andExpect(MockMvcResultMatchers.status()
				.isUnauthorized());
	}

	// @formatter:off
	@DisplayName("given USER role, when GET on /secured, then OK")
	// @formatter:on
	@Test
	@WithMockUser(roles = "USER")
	void test1() throws Exception {
		// when
		mockMvc.perform(MockMvcRequestBuilders.get("/secured"))

			// then
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
