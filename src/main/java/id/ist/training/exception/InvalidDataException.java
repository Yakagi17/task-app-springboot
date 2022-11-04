package id.ist.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error, data violation on validation")
public class InvalidDataException extends RuntimeException {
	private static final long serialVersionUID = 7545410064997128106L;
}
