package model;

public class Person {

	Integer initialFloor;
	Integer destinationFloor;

	public Person(Integer initialFloor, Integer destinationFloor) {
		super();
		this.initialFloor = initialFloor;
		this.destinationFloor = destinationFloor;
	}

	public Integer getInitialFloor() {
		return initialFloor;
	}

	public void setInitialFloor(Integer initialFloor) {
		this.initialFloor = initialFloor;
	}

	public Integer getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(Integer destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

}
