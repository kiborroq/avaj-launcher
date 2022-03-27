package ru.school.avaj.aircrafts;

import ru.school.avaj.SimulationLogger;
import ru.school.avaj.WeatherTower;

import java.util.List;
import java.util.Map;

import static ru.school.avaj.WeatherType.FOG;
import static ru.school.avaj.WeatherType.RAIN;
import static ru.school.avaj.WeatherType.SNOW;
import static ru.school.avaj.WeatherType.SUN;
import static ru.school.avaj.aircrafts.AircraftType.JETPLANE;

public class JetPlane extends Aircraft implements Flyable {

	private final static Map<String, String> MESSAGES = Map.of(
			SUN, "I am the fastest in sun day!",
			RAIN, "It's raining. Better watch out for lightings.",
			FOG, "Fog is not a problem.",
			SNOW, "OMG! Winter is coming!");

	private final static Map<String, List<Integer>> COORDINATE_CHANGES = Map.of(
			SUN, List.of(0, 10, 2),
			RAIN, List.of(0, 5, 0),
			FOG, List.of(0, 1, 0),
			SNOW, List.of(0, 0, -7));

	private final static SimulationLogger LOGGER = SimulationLogger.getLogger();

	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, JETPLANE, coordinates);
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
