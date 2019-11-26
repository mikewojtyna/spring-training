package pro.buildmysoftware.spring.core.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TweetRepo tweetRepo;
	@MockBean
	private TweetAppService tweetAppService;

	// @formatter:off
	@DisplayName(
		"when GET on /api/tweets, " +
		"then empty collection is returned"
	)
	// @formatter:on
	@Test
	void test() throws Exception {
		// when
		tweetRepo.deleteAll();
		mockMvc.perform(get("/api/tweets"))

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", is(empty())));
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
		// given
		// populate with some data
		tweetRepo.saveAll(Stream
			.generate(() -> new Tweet("message" + UUID
				.randomUUID(), "author" + UUID.randomUUID()))
			.limit(100).collect(Collectors.toList()));

		// when
		mockMvc.perform(get("/api/tweets").param("page", "2")
			.param("size", "3")).andDo(print())

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.number", is(2)))
			.andExpect(jsonPath("$.size", is(3)))
			.andExpect(jsonPath("$.content", hasSize(3)));
	}

	// @formatter:off
	@DisplayName("when POST on /api/tweets, then new tweet is created")
	// @formatter:on
	@Test
	void testCreate() throws Exception {
		// given
		tweetRepo.deleteAll();
		String json =
			// @formatter:off
			"{ \"message\": \"hello\", \"author\": \"goobar\" }";
			// @formatter:on

		// when
		mockMvc.perform(
			// @formatter:off
			post("/api/tweets")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json)
			// @formatter:on
		)

			// then
			.andDo(print()).andExpect(status().isCreated());

		mockMvc.perform(get("/api/tweets")).andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].message", is("hello")))
			.andExpect(jsonPath("$[0].author", is("goobar")));
	}

	// @formatter:off
	@DisplayName(
		"GET on /api/tweets/{id}, then single tweet is returned"
	)
	// @formatter:on
	@Test
	void testSingleTweet() throws Exception {
		// given
		tweetRepo.deleteAll();
		Tweet savedTweet = tweetRepo.save(new Tweet("hello", "goobar"
		));

		// when
		mockMvc.perform(get("/api/tweets/{id}", savedTweet.getId()
			.toString())).andDo(print())

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is(savedTweet
				.getMessage())))
			.andExpect(jsonPath("$.id", is(savedTweet.getId()
				.toString())));
	}

	// @formatter:off
	@DisplayName(
		"GET on /api/tweets/{id}, then 404 status is returned, " +
		"when tweet doesn't exist"
	)
	// @formatter:on
	@Test
	void testSingleTweetNotFound() throws Exception {
		// given
		tweetRepo.deleteAll();

		// when
		mockMvc.perform(get("/api/tweets/{id}", UUID.randomUUID()
			.toString()))

			// then
			.andExpect(status().isNotFound());
	}

	@DisplayName("empty optional example")
	@Test
	void testEmpty() throws Exception {
		mockMvc.perform(get("/api/tweets/empty")).andDo(print())
			.andExpect(content().string("null"));
	}

	@DisplayName("search by msg")
	@Test
	void testSearchMsg() throws Exception {
		// given
		when(tweetAppService.findByMsg("hello"))
			.thenReturn(List.of(tweetWithMsg("hello")));

		// when
		mockMvc.perform(get("/api/tweets").param("msg", "hello"))

			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].message", is("hello")));
	}

	private Tweet tweetWithMsg(String msg) {
		return TweetTestUtils.tweetWithMsg(msg);
	}
}
