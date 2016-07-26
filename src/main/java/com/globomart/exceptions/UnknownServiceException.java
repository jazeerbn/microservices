package com.globomart.exceptions;

public class UnknownServiceException extends Exception{

	private static final long serialVersionUID = 472327974391682482L;

	public UnknownServiceException() {
		super();
	}

	public UnknownServiceException(String message) {
		super(message);
	}

	public UnknownServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownServiceException(Throwable cause) {
		super(cause);
	}
}
