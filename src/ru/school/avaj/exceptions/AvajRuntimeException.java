package ru.school.avaj.exceptions;

public class AvajRuntimeException extends RuntimeException {
	public AvajRuntimeException(String message) {
		super("Runtime error: " + message);
	}

	public AvajRuntimeException(String message, Throwable cause) {
		super("Runtime error: " + message, cause);
	}
}
