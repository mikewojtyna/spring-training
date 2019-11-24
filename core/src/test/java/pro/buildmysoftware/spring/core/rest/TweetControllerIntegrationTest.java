package pro.buildmysoftware.spring.core.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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

	// @formatter:off
	@DisplayName("find page")
	// @formatter:on
	@Test
	void testPage() throws Exception {
		// when
		mockMvc.perform(get("/api/tweets").param("page", "2")
			.param("size", "3"))
			.andDo(MockMvcResultHandlers.print())

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.number", is(2)))
			.andExpect(jsonPath("$.size", is(3)))
			.andExpect(jsonPath("$.content", hasSize(3)));
	}
}
