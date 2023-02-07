package sk.upjs.bugster_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BugNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6806120645998269030L;

	public BugNotFoundException() {
		super();
	}

	public BugNotFoundException(String message) {
		super(message);
	}
}
