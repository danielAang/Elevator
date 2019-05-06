package model;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

	private Integer currentPosition;
	private List<Person> passengers = new ArrayList<Person>();

	public Elevator(Integer currentPosition) {
		super();
		this.currentPosition = currentPosition;
	}

	public Integer getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void addPerson(Person person) {
		if (passengers == null)
			return;
		passengers.add(person);
	}
	
	public void printInformation(String information) {
		System.out.println(information);
	}

}