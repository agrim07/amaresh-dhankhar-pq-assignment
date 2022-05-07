package org.payconiq.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PayconiqExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PayconiqExceptionHandler.class);

	@ExceptionHandler({StockNotFoundException.class})
	public ResponseEntity<String> stockNotFoundResponse(Exception exception, final WebRequest req) {
		LOGGER.error("StockNotFoundException: "+ exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<String> internalServerErrorResponse(Exception exception, final WebRequest req) {
		LOGGER.error("RuntimeException: "+ exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
