package com.reconciliation.demo.service.exception;

public class ServiceClientException extends ServiceException {

	private static final long serialVersionUID = 3900485246853552467L;

	public ServiceClientException( String message, Throwable cause, boolean enableSuppression,
								  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceClientException(String message) {
		super(errorDetail, message);
	}

	public ServiceClientException(Throwable cause) {
		super(errorDetail, cause);
	}

	public ServiceClientException(ServiceException e) {
		super(e);
	}
}
