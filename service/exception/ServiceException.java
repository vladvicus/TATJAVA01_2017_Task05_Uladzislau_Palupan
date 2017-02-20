package com.epam.parsing.service.exception;

/**

 */
public class ServiceException extends Exception{
    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ServiceException(String message, Exception cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ServiceException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ServiceException(Exception cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
