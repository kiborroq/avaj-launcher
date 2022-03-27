package ru.school.avaj.exceptions;

public class AvajArgumentsException extends RuntimeException {
	public AvajArgumentsException(String message) {
		super("Argument error: " + message);
	}
}
