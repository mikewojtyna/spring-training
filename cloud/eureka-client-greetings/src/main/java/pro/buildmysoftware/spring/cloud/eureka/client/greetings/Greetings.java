package pro.buildmysoftware.spring.cloud.eureka.client.greetings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Greetings {

	private final String msg;

	@JsonCreator
	public Greetings(@JsonProperty("msg") String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Greeting{" + "msg='" + msg + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Greetings greeting = (Greetings) o;
		return Objects.equals(msg, greeting.msg);
	}

	@Override
	public int hashCode() {
		return Objects.hash(msg);
	}

	public String getMsg() {
		return msg;
	}
}
