package sk.upjs.bugster_rest;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError handleDaoException(NumberFormatException e) {
		return new ApiError(HttpStatus.BAD_REQUEST.value(), "Object id must be number");
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		return new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleNoSuchElementException(NoSuchElementException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleUserNotFoundException(UserNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleProjectNotFoundException(ProjectNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleRoleNotFoundException(RoleNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(UserInProjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleUserInProjectNotFoundException(UserInProjectNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(BugNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleBugNotFoundException(BugNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(SeverityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleSeverityNotFoundException(SeverityNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(StatusNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleStatusNotFoundException(StatusNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

}