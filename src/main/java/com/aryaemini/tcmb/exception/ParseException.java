package com.aryaemini.tcmb.exception;

public class ParseException extends Exception {

	private static final long serialVersionUID = 100L;
	private String message;
	private Throwable cause;

	public ParseException() {}

	public ParseException(String message) {
		this.message = message;
	}

	public ParseException(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public Throwable getCause() {
		return this.cause;
	}

}
