package ru.school.avaj.aircrafts;

import java.util.List;

public class Coordinates {

	private static final int MAX_HEIGHT = 100;

	private int longitude;
	private int latitude;
	private int height;

	Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getHeight() {
		return height;
	}

	public void update(List<Integer> coordinates) {
		increaseLongitude(coordinates.get(0));
		increaseLatitude(coordinates.get(1));
		increaseHeight(coordinates.get(2));
	}

	private void increaseLongitude(int value) {
		this.longitude = getChangedCoordinate(longitude, value, Integer.MAX_VALUE);
	}

	private void increaseLatitude(int value) {
		this.latitude = getChangedCoordinate(latitude, value, Integer.MAX_VALUE);
	}

	private void increaseHeight(int value) {
		this.height = getChangedCoordinate(height, value, MAX_HEIGHT);
	}

	private int getChangedCoordinate(int currentValue, int delta, int maxValue) {
		int newValue;

		if (delta > 0 && maxValue - currentValue <= delta) {
			newValue = maxValue;
		} else {
			newValue = currentValue + delta;
		}

		return Math.max(newValue, 0);
	}

	@Override
	public String toString() {
		return "(" + longitude +
				", " + latitude +
				", " + height +
				')';
	}
}
