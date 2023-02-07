package sk.upjs.bugster_rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -680612064515651L;

	public ProjectNotFoundException() {
		super();
	}

	public ProjectNotFoundException(String message) {
		super(message);
	}
}
