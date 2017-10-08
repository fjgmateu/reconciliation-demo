package com.asefa.service.common.exception.checked;



public class ServiceBusinessException extends ServiceException {

	private static final long serialVersionUID = -7165242146686360882L;

	public ServiceBusinessException(String message, Throwable cause,
									boolean enableSuppression, boolean writableStackTrace) {
		super(errorDetail, message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceBusinessException(String message, Throwable cause) {
		super( message, cause);
	}

	public ServiceBusinessException( String message) {
		super(message);
	}

	public ServiceBusinessException( Throwable cause) {
		super(cause);
	}

	public ServiceBusinessException(ServiceException e) {
		super(e);
	}

}
