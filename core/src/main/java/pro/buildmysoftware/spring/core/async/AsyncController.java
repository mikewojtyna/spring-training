package pro.buildmysoftware.spring.core.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/async")
public class AsyncController {

	private AsyncService asyncService;

	public AsyncController(AsyncService asyncService) {
		this.asyncService = asyncService;
	}

	@GetMapping
	public CompletableFuture<Result> async() {
		return asyncService.process();
	}
}
