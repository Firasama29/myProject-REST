package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4830898326421615580L;

	public ProjectNotFoundException(String message) {
		super(String.format("Project does not exist"));
	}
}
