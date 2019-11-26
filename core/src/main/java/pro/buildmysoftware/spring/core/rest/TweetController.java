package pro.buildmysoftware.spring.core.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	private TweetRepo repo;

	public TweetController(TweetRepo repo) {
		this.repo = repo;
	}

	@GetMapping
	public Collection<Tweet> findAllTweets() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tweet> findTweet(@PathVariable("id") String uuid) {
		return repo.findById(UUID.fromString(uuid))
			.map(t -> ResponseEntity.status(HttpStatus.OK).body(t))
			.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "msg")
	public Collection<Tweet> findByMessage(@RequestParam("msg") String msg) {
		return repo.findByMessage(msg);
	}

	@GetMapping("/empty")
	public Optional<String> returnEmptyOptional() {
		return Optional.empty();
	}

	@PostMapping
	public ResponseEntity<?> createTweet(@RequestBody Tweet tweet) {
		Tweet savedTweet = repo.save(tweet);
		return ResponseEntity.created(URI.create(String
			.format("/api/tweets/%s", savedTweet.getId()
				.toString()))).build();
	}

	@PutMapping("/{id}")
	public void replace(@PathVariable("id") String uuid,
			    @RequestBody Tweet tweet) {
		tweet.setId(UUID.fromString(uuid));
		repo.save(tweet);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String uuid) {
		repo.deleteById(UUID.fromString(uuid));
	}

	@PatchMapping("/{id}")
	public void patch(@PathVariable("id") String uuid,
			  @RequestBody Tweet patch) {
		repo.findById(UUID.fromString(uuid)).ifPresent(t -> {
			String messageFromPatch = patch.getMessage();
			if (messageFromPatch != null) {
				t.setMessage(patch.getMessage());
			}
			String authorFromPatch = patch.getAuthor();
			if (authorFromPatch != null) {
				t.setAuthor(authorFromPatch);
			}
			repo.save(t);
		});
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
