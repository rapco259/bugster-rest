package sk.upjs.bugster_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserInProjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6806120645998299456L;

	public UserInProjectNotFoundException() {
		super();
	}

	public UserInProjectNotFoundException(String message) {
		super(message);
	}
}
