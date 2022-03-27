package ru.school.avaj;

import ru.school.avaj.aircrafts.Flyable;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	private final List<Flyable> observers = new ArrayList<>();
	private final static SimulationLogger LOGGER = SimulationLogger.getLogger();

	public void register(Flyable flyable) {
		if (!observers.contains(flyable)) {
			observers.add(flyable);
			LOGGER.register(flyable);
		}
	}

	public void unregister(Flyable flyable) {
		if (observers.contains(flyable)) {
			observers.remove(flyable);
			LOGGER.unregister(flyable);
		}
	}

	protected void conditionsChanged() {
		new ArrayList<>(observers).forEach(Flyable::updateConditions);
	}
}
