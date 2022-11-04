package id.ist.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import id.ist.training.exception.InvalidDataException;
import id.ist.training.exception.TaskNotFoundException;
import lombok.NoArgsConstructor;

@RestControllerAdvice
@NoArgsConstructor
public class GlobalTaskControllerAdvice {
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data validation is violated")
	@ExceptionHandler(InvalidDataException.class)
	public void invlaidData() {

	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There's isnt any data match you're seaching" )
	@ExceptionHandler(TaskNotFoundException.class)
	public void taskDataNotFound() {
	}

}
