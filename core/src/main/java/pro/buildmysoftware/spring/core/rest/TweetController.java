package pro.buildmysoftware.spring.core.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private Collection<Tweet> tweets = new ConcurrentLinkedDeque<>();
	private TweetRepo repo;

	public TweetController(TweetRepo repo) {
		this.repo = repo;
		repo.saveAll(Stream.generate(() -> new Tweet("message" + UUID
			.randomUUID(), "author" + UUID.randomUUID())).limit(100)
			.collect(Collectors.toList()));
	}

	@GetMapping
	public Collection<Tweet> findAllTweets() {
		return tweets;
	}

	@GetMapping(params = {"page"})
	public Page<Tweet> findTweetPage(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@PostMapping("/exception")
	public void throwEx() {
		throw new RuntimeException("This method throws " +
			"exception");
	}
}
