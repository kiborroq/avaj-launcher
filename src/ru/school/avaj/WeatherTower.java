package ru.school.avaj;

import ru.school.avaj.aircrafts.Coordinates;

public class WeatherTower extends Tower {

	public WeatherTower() {
		super();
	}

	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	void changeWeather() {
		super.conditionsChanged();
	}
}
