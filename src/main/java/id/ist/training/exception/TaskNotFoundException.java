package id.ist.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "There isn't any task that match you're searching")
public class TaskNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -4860817578271610138L;
}
