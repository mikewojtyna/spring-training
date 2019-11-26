package pro.buildmysoftware.spring.core.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TweetAppServiceRepoDelegatingImpl implements TweetAppService {

	private TweetRepo tweetRepo;

	public TweetAppServiceRepoDelegatingImpl(TweetRepo tweetRepo) {
		this.tweetRepo = tweetRepo;
	}

	@Override
	public void createNewTweet(String msg) {
		tweetRepo.save(new Tweet(msg, defaultAuthor()));
	}

	@Override
	public Collection<Tweet> findByMsg(String msg) {
		return tweetRepo.findAll().stream()
			.filter(t -> msg.equals(t.getMessage()))
			.collect(Collectors.toList());
		// Now, try to uncomment this code and run test "find by
		// message using mocks" again - now, this time it should pass
		// return tweetRepo.findByMessage(msg);
	}

	private String defaultAuthor() {
		return "goobar";
	}
}
