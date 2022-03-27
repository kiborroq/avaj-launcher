package ru.school.avaj.aircrafts;

import ru.school.avaj.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower weatherTower);
}
