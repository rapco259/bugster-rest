package sk.upjs.bugster_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6806120645998219450L;

	public StatusNotFoundException() {
		super();
	}

	public StatusNotFoundException(String message) {
		super(message);
	}
}
