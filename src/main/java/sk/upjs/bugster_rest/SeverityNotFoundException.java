package sk.upjs.bugster_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SeverityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6806450645998299450L;

	public SeverityNotFoundException() {
		super();
	}

	public SeverityNotFoundException(String message) {
		super(message);
	}
}
