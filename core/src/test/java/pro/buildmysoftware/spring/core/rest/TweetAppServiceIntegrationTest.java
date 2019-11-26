package pro.buildmysoftware.spring.core.rest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pro.buildmysoftware.spring.core.rest.TweetTestUtils.tweetWithMsg;

@SpringBootTest
public class TweetAppServiceIntegrationTest {

	@Autowired
	private TweetAppService tweetAppService;

	@DisplayName("find by message")
	@Test
	void test() throws Exception {
		// given
		tweetAppService.createNewTweet("hello");
		tweetAppService.createNewTweet("hi");
		tweetAppService.createNewTweet("hello");

		// when
		Collection<Tweet> tweets = tweetAppService.findByMsg("hello");

		// then
		assertThat(tweets).hasSize(2).extracting(t -> t.getMessage())
			.contains("hello");
	}

	@DisplayName("find by message using mocks")
	@Test
	@Disabled
	void testMock() throws Exception {
		// given
		TweetRepo tweetRepo = inMemoryFakeRepo(tweetWithMsg("hello"),
			tweetWithMsg("hello"));
		TweetAppService tweetAppService =
			new TweetAppServiceRepoDelegatingImpl(tweetRepo);

		// when
		Collection<Tweet> allTweets = tweetAppService
			.findByMsg("hello");

		// then
		assertThat(allTweets).hasSize(2);
	}

	private TweetRepo inMemoryFakeRepo(Tweet... tweet) {
		// we should not mock any specific methods here, because we
		// could implement the other way and still have exactly the
		// same results (like findAll and call stream.filter())
		TweetRepo mock = mock(TweetRepo.class);
		when(mock.findByMessage(anyString()))
			.thenReturn(List.of(tweet));
		return mock;
	}
}
