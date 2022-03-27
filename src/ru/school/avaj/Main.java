package ru.school.avaj;

import ru.school.avaj.exceptions.AvajArgumentsException;
import ru.school.avaj.exceptions.AvajRuntimeException;
import ru.school.avaj.exceptions.AvajScenarioException;

public class Main {
	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				throw new AvajArgumentsException("Invalid arguments number");
			}

			Scenario scenario = new Scenario(args[0]);
			WeatherTower weatherTower = new WeatherTower();
			scenario.getFlyables().forEach(flyable -> flyable.registerTower(weatherTower));

			for (int simulation = scenario.getSimulationCount(); simulation > 0; simulation--) {
				weatherTower.changeWeather();
			}
		} catch (AvajRuntimeException | AvajScenarioException | AvajArgumentsException e) {
			System.out.println(e.getMessage());
		} finally {
			SimulationLogger.getLogger().close();
		}
	}
}
