package com.demo.exception;

public class InvalidSortOrderException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSortOrderException(String message) {
        super(message);
    }

}
