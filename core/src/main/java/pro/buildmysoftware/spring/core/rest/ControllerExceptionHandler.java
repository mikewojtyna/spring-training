package pro.buildmysoftware.spring.core.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ExceptionWrapper> handle(Exception exception) {
		ExceptionWrapper exceptionWrapper = new ExceptionWrapper();
		exceptionWrapper.setExCode("EX_ERR");
		exceptionWrapper.setMsg(exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(exceptionWrapper);
	}
}
