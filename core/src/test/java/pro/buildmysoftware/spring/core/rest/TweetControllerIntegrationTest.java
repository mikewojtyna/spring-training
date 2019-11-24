package pro.buildmysoftware.spring.core.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TweetControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	// @formatter:off
	@DisplayName(
		"when GET on /api/tweets, " +
		"then empty collection is returned"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		mockMvc.perform(get("/api/tweets"))

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers
				.is(Matchers.empty())));
	}
}
