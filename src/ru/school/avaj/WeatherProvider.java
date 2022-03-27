package ru.school.avaj;

import ru.school.avaj.aircrafts.Coordinates;

import java.util.List;
import java.util.Random;

import static ru.school.avaj.WeatherType.FOG;
import static ru.school.avaj.WeatherType.RAIN;
import static ru.school.avaj.WeatherType.SNOW;
import static ru.school.avaj.WeatherType.SUN;

public class WeatherProvider {
	private static WeatherProvider provider;
	private final static List<String> WEATHERS = List.of(FOG, RAIN, SUN, SNOW);
	private final static Random random = new Random();

	private WeatherProvider() {}

	public static WeatherProvider getProvider() {
		if (provider == null) {
			provider = new WeatherProvider();
		}
		return provider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		random.setSeed((long) coordinates.getHeight() * coordinates.getLatitude() * coordinates.getLongitude() + System.currentTimeMillis());
		return WEATHERS.get(random.nextInt(4));
	}
}
