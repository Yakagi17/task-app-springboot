package id.ist.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileIoException extends RuntimeException {
	private static final long serialVersionUID = -8521050141000484920L;
}
