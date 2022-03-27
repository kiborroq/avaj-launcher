package ru.school.avaj.aircrafts;


public abstract class Aircraft {
	protected long id;
	protected String name;
	protected String type;
	protected Coordinates coordinates;
	private static long idCounter = 1;

	protected Aircraft(String name, String type, Coordinates coordinates) {
		this.id = nextId();
		this.name = name;
		this.type = type;
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	@Override
	public String toString() {
		return type + "#" + name + "(" + id + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Aircraft aircraft = (Aircraft) o;

		return id == aircraft.id;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	private long nextId() {
		return idCounter++;
	}
}
