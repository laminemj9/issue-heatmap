package com.issues.exception;
 
public class IssueException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public IssueException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public IssueException() {
		super();
	}
}