package com.aryaemini.tcmb.exception;

public class ExchangeRateException extends RuntimeException {

	private static final long serialVersionUID = 200L;
	private String message;
	private Throwable cause;

	public ExchangeRateException() {}

	public ExchangeRateException(String message) {
		this.message = message;
	}

	public ExchangeRateException(String message, Throwable cause) {
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
