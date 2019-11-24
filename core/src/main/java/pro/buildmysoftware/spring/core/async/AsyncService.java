package pro.buildmysoftware.spring.core.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

	@Async
	public CompletableFuture<Result> process() {
		try {
			Thread.sleep(2000);
			return CompletableFuture
				.completedFuture(new Result("OK"));
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
