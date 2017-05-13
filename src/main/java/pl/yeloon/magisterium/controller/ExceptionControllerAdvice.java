package pl.yeloon.magisterium.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e){
		//TODO wymusic zapisywanie jak najwiekszej ilosci informacji
		logger.error("EXCEPTION!! " + e.getMessage());
		return "generic_error";
	}
	
}
