package ru.school.avaj;

import ru.school.avaj.aircrafts.AircraftFactory;
import ru.school.avaj.aircrafts.Flyable;
import ru.school.avaj.exceptions.AvajRuntimeException;
import ru.school.avaj.exceptions.AvajScenarioException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.school.avaj.aircrafts.AircraftType.BALOON;
import static ru.school.avaj.aircrafts.AircraftType.HELICOPTER;
import static ru.school.avaj.aircrafts.AircraftType.JETPLANE;

public class Scenario {
	private final static MessageDigest MESSAGE_DIGEST;
	private final static int PARAMETERS_NUMBER = 5;
	private final static Map<String, String> AIRCRAFT_TYPES = Map.of(
			HELICOPTER.toLowerCase(), HELICOPTER,
			JETPLANE.toLowerCase(), JETPLANE,
			BALOON.toLowerCase(), BALOON);

	private final File file;
	private final AircraftFactory factory;

	private int simulationCount;
	private final List<Flyable> flyables;
	private int line = 0;

	static {
		try {
			MESSAGE_DIGEST = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new AvajRuntimeException(e.getMessage(), e);
		}
	}

	public Scenario(String scenario) {
		file = new File(scenario);
		factory = new AircraftFactory();
		flyables = new ArrayList<>();
		parse();
	}

	public int getSimulationCount() {
		return simulationCount;
	}

	public List<Flyable> getFlyables() {
		return flyables;
	}

	private void parse() {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			simulationCount = parseIntField(getNextLine(br), 1, Integer.MAX_VALUE, "Simulation number");

			for (String aircraft = getNextLine(br); aircraft != null; aircraft = getNextLine(br)) {
				parseAircraft(aircraft);
			}

			if (flyables.size() == 0) {
				throw new AvajScenarioException("No aircraft specified.", line);
			}
		} catch (IOException e) {
			throw new AvajRuntimeException(e.getMessage(), e);
		}
	}

	private void parseAircraft(String aircraft) {
		if (aircraft == null || aircraft.isEmpty()) {
			throw new AvajScenarioException("Empty line", line);
		}

		String[] strings = aircraft.split(" ");
		if (strings.length != PARAMETERS_NUMBER) {
			throw new AvajScenarioException(String.format("Invalid aircraft description (number of parameters not %d).", PARAMETERS_NUMBER), line);
		}

		String type = strings[0];
		if (type == null || type.isEmpty()) {
			throw new AvajScenarioException("Aircraft type is not specified.", line);
		} else if (!AIRCRAFT_TYPES.containsKey(type.toLowerCase())) {
			throw new AvajScenarioException(String.format("Unknown aircraft type '%s'.", type), line);
		} else {
			type = AIRCRAFT_TYPES.get(type.toLowerCase());
		}

		String name = strings[1];
		if (name == null || name.isEmpty()) {
			throw new AvajScenarioException("Aircraft name is not specified.", line);
		}

		int longitude = parseIntField(strings[2], 1, Integer.MAX_VALUE, "Aircraft longitude");
		int latitude = parseIntField(strings[3], 1, Integer.MAX_VALUE, "Aircraft latitude");
		int height = parseIntField(strings[4], 0, 100, "Aircraft height");

		flyables.add(factory.newAircraft(type, name, longitude, latitude, height));
	}

	private int parseIntField(String nbr, int min, int max, String fieldName) {
		if (nbr == null || nbr.isEmpty()) {
			throw new AvajScenarioException(String.format("%s is not specified.", fieldName), line);
		}

		long number;
		try	{
			number = Long.parseLong(nbr);
		} catch (NumberFormatException e) {
			throw new AvajScenarioException(String.format("%s '%s' is not numeric.", fieldName, nbr), line, e);
		}

		if (number < min) {
			throw new AvajScenarioException(String.format("%s '%d' is less than %d.", fieldName, number, min), line);
		}

		if (number > max) {
			throw new AvajScenarioException(String.format("%s '%d' is more than %d.", fieldName, number, max), line);
		}

		return (int) number;
	}

	private String getNextLine(BufferedReader br) throws IOException {
		line++;
		return Optional.ofNullable(br.readLine()).map(String::trim).orElse(null);
	}
}
