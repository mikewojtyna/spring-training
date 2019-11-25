package pro.buildmysoftware.spring.core.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private TweetRepo repo;

	public TweetController(TweetRepo repo) {
		this.repo = repo;
		repo.saveAll(Stream.generate(() -> new Tweet("message" + UUID
			.randomUUID(), "author" + UUID.randomUUID())).limit(100)
			.collect(Collectors.toList()));
	}

	@GetMapping
	public Collection<Tweet> findAllTweets() {
		return repo.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTweet(@RequestBody Tweet tweet) {
		repo.save(tweet);
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
