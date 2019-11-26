package pro.buildmysoftware.spring.core.rest;

import java.util.Collection;

public interface TweetAppService {

	void createNewTweet(String msg);

	Collection<Tweet> findByMsg(String msg);
}
