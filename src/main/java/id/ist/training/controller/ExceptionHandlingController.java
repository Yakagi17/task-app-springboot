package id.ist.training.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import id.ist.training.exception.InvalidDataException;
import id.ist.training.exception.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExceptionHandlingController {
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidDataException.class)
	public ModelAndView invlaidData(HttpServletRequest req, Exception ex){
		log.error("Request: " + req.getRequestURL() + " raised " + ex);
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", ex);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    return mav;
 }
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(TaskNotFoundException.class)
	public String taskDataNotFound() {
		return "There's isnt Task in the database that match you're seaching.";
	}

}
