package pro.buildmysoftware.spring.core.async;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AsyncServiceTest {

	@Autowired
	private AsyncService asyncService;

	@DisplayName("async example")
	@Test
	void test() throws Exception {
		// when
		CompletableFuture<Result> result = asyncService.process();

		// then
		assertThat(result.isDone()).isFalse(); // should return
		// immediately, since we are using @Async on AsyncService
		// method
		assertThat(result.get()).isEqualTo(new Result("OK"));
	}
}