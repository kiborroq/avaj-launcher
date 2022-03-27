package ru.school.avaj;

import ru.school.avaj.aircrafts.Aircraft;
import ru.school.avaj.aircrafts.Flyable;
import ru.school.avaj.exceptions.AvajRuntimeException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SimulationLogger {

	private final static String FILE_NAME = "simulation.txt";

	private static BufferedWriter writer;
	private static SimulationLogger logger;

	private SimulationLogger() {
		try {
			writer = new BufferedWriter(new FileWriter(FILE_NAME, false));
		} catch (IOException e) {
			throw new AvajRuntimeException(e.getMessage(), e);
		}
	}

	public static SimulationLogger getLogger() {
		if (logger == null) {
			logger = new SimulationLogger();
		}
		return logger;
	}

	public void register(Flyable flyable) {
		write(String.format("Tower says: %s registered to weather tower.", flyable));
	}

	public void unregister(Flyable flyable) {
		write(String.format("Tower says: %s unregistered from weather tower.", flyable));
	}

	public void speak(Aircraft aircraft, String message) {
		write(String.format("%s: %s", aircraft, message));
	}

	public void landing(Aircraft aircraft) {
		write(String.format("%s: Land %s", aircraft, aircraft.getCoordinates()));
	}

	public void close() {
		try {
			writer.close();
			logger = null;
		} catch (IOException e) {
			throw new AvajRuntimeException(e.getMessage(), e);
		}
	}

	private void write(String message) {
		try {
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			throw new AvajRuntimeException(e.getMessage(), e);
		}
	}
}
