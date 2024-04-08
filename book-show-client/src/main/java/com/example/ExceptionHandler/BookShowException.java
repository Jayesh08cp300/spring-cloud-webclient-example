package com.example.ExceptionHandler;

public class BookShowException extends Exception {
	public BookShowException() {
	}

	public BookShowException(String message) {
		super(message);
	}

	public BookShowException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookShowException(Throwable cause) {
		super(cause);
	}

	public BookShowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
