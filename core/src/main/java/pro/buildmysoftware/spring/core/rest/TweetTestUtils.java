package pro.buildmysoftware.spring.core.rest;

public class TweetTestUtils {

	public static Tweet tweetWithMsg(String msg) {
		return new Tweet(msg, "goobar");
	}
}
