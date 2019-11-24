package pro.buildmysoftware.spring.core.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private Collection<Tweet> tweets = new ConcurrentLinkedDeque<>();

	@GetMapping
	public Collection<Tweet> findAllTweets() {
		return tweets;
	}
}
