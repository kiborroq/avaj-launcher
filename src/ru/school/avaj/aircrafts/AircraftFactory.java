package ru.school.avaj.aircrafts;

import ru.school.avaj.exceptions.AvajRuntimeException;

import static ru.school.avaj.aircrafts.AircraftType.BALOON;
import static ru.school.avaj.aircrafts.AircraftType.HELICOPTER;
import static ru.school.avaj.aircrafts.AircraftType.JETPLANE;

public class AircraftFactory {
	public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		Coordinates coordinates = new Coordinates(longitude, latitude, height);
		switch (type) {
			case HELICOPTER:
				return new Helicopter(name, coordinates);
			case JETPLANE:
				return new JetPlane(name, coordinates);
			case BALOON:
				return new Baloon(name, coordinates);
			default:
				throw new AvajRuntimeException(String.format("Unknown aircraft '%s'", type));
		}
	}
}
