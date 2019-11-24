package pro.buildmysoftware.spring.core.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String message;
	private String author;

	@JsonCreator
	public Tweet(@JsonProperty("message") String message, @JsonProperty(
		"author") String author) {
		this.message = message;
		this.author = author;
	}
}
