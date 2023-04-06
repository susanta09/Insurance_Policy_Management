package com.insurance_policy.API_ExpectionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 9076343618291376342L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	

}
