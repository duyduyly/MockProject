package com.example.demo.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public FileStorageException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
