package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectAlreadyExists extends RuntimeException {

	public ProjectAlreadyExists(String message) {
		super(String.format("Project already exists!"));
	}
}
