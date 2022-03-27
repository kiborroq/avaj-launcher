package ru.school.avaj.exceptions;

public class AvajScenarioException extends RuntimeException {
	public AvajScenarioException(String message, int line) {
		super("Scenario error (line=" + line + "): " + message);
	}

	public AvajScenarioException(String message, int line, Throwable cause) {
		super("Scenario error (line=" + line + "): " + message, cause);
	}
}
