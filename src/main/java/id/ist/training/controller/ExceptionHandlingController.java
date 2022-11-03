package id.ist.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import id.ist.training.exception.InvalidDataException;
import id.ist.training.exception.TaskNotFoundException;

@Controller
public class ExceptionHandlingController {
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidDataException.class)
	public String invlaidData(){
		return "Invalid data input";
 }
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(TaskNotFoundException.class)
	public String taskDataNotFound() {
		return "There's isnt Task in the database that match you're seaching.";
	}

}
