package com.cognizant.membermicroservice.exception;

/**
 * 
 * This class used to throw exception if the required data is not available this
 * class extends the RuntimeException class.
 *
 */

public class PolicyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PolicyNotFoundException() {
		super();
	}

	public PolicyNotFoundException(String message) {
		super(message);
	}

}
