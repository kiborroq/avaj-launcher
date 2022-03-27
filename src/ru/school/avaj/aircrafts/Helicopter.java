package ru.school.avaj.aircrafts;

import ru.school.avaj.SimulationLogger;
import ru.school.avaj.WeatherTower;
import ru.school.avaj.exceptions.AvajRuntimeException;

import java.util.List;
import java.util.Map;

import static ru.school.avaj.WeatherType.FOG;
import static ru.school.avaj.WeatherType.RAIN;
import static ru.school.avaj.WeatherType.SNOW;
import static ru.school.avaj.WeatherType.SUN;
import static ru.school.avaj.aircrafts.AircraftType.HELICOPTER;

public class Helicopter extends Aircraft implements Flyable {

	private final static Map<String, String> MESSAGES = Map.of(
			SUN, "This is hot.",
			RAIN, "I hate the rain!",
			FOG, "I can't see anything. Please help!",
			SNOW, "My rotor is going to freeze.");

	private final static Map<String, List<Integer>> COORDINATE_CHANGES = Map.of(
			SUN, List.of(10, 0, 2),
			RAIN, List.of(5, 0, 0),
			FOG, List.of(1, 0, 0),
			SNOW, List.of(0, 0, -12));

	private final static SimulationLogger LOGGER = SimulationLogger.getLogger();

	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, HELICOPTER, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);

		coordinates.update(COORDINATE_CHANGES.get(weather));
		if (coordinates.getHeight() > 0) {
			LOGGER.speak(this, MESSAGES.get(weather));
		} else {
			LOGGER.landing(this);
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		if (coordinates.getHeight() > 0) {
			this.weatherTower = weatherTower;
			weatherTower.register(this);
		}
	}
}
