/**
 * 
 */
package com.bookmanagement.exception;

/**
 * @author Dino
 */
public class MyResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8990467090574702556L;

	public MyResourceNotFoundException() {
		super();
	}

	public MyResourceNotFoundException(String message) {
		super(message);
	}

	public MyResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
