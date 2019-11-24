package pro.buildmysoftware.spring.core.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Tweet {

	private String message;
	private String author;

	@JsonCreator
	public Tweet(@JsonProperty("message") String message, @JsonProperty(
		"author") String author) {
		this.message = message;
		this.author = author;
	}
}
