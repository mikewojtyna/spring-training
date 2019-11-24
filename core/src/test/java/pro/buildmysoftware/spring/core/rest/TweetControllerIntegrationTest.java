package pro.buildmysoftware.spring.core.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
			.andExpect(jsonPath("$", is(Matchers.empty())));
	}

	// @formatter:off
	@DisplayName("handle exception using advice")
	// @formatter:on
	@Test
	void testEx() throws Exception {
		// when
		mockMvc.perform(post("/api/tweets/exception"))

			// then
			.andExpect(status().isInternalServerError())
			.andExpect(jsonPath("$.msg", is("This " + "method " +
				"throws exception")))
			.andExpect(jsonPath("$.exCode", is("EX_ERR")));
	}
}
