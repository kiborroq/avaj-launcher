package ru.school.avaj.aircrafts;

import ru.school.avaj.SimulationLogger;
import ru.school.avaj.WeatherTower;

import java.util.List;
import java.util.Map;

import static ru.school.avaj.WeatherType.FOG;
import static ru.school.avaj.WeatherType.RAIN;
import static ru.school.avaj.WeatherType.SNOW;
import static ru.school.avaj.WeatherType.SUN;
import static ru.school.avaj.aircrafts.AircraftType.BALOON;

public class Baloon extends Aircraft implements Flyable {

	private final static Map<String, String> MESSAGES = Map.of(
			SUN, "Let's enjoy the good weather and take some pics.",
			RAIN, "Damn you rain! You messed up my baloon.",
			FOG, "Doesn't matter at all! Ha-Ha",
			SNOW, "It's snowing. We're gonna crash.");

	private final static Map<String, List<Integer>> COORDINATE_CHANGES = Map.of(
			SUN, List.of(2, 0, 4),
			RAIN, List.of(0, 0, -5),
			FOG, List.of(0, 0, -3),
			SNOW, List.of(0, 0, -15));

	private final static SimulationLogger LOGGER = SimulationLogger.getLogger();

	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, BALOON, coordinates);
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
