package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import enumeration.Direction;
import model.Elevator;
import model.Person;

public class ElevatorController {

	private Elevator elevator;
	private Integer totalFloors = 10;
	List<Integer> upRequests = new ArrayList<Integer>();
	List<Integer> downRequests = new ArrayList<Integer>();

	public ElevatorController(Elevator elevator) {
		super();
		this.elevator = elevator;
	}

	public Elevator getElevator() {
		return elevator;
	}

	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}

	public Integer getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(Integer totalFloors) {
		this.totalFloors = totalFloors;
	}

	public List<Integer> getUpRequests() {
		return upRequests;
	}

	public void setUpRequests(List<Integer> upRequests) {
		this.upRequests = upRequests;
	}

	public List<Integer> getDownRequests() {
		return downRequests;
	}

	public void setDownRequests(List<Integer> downRequests) {
		this.downRequests = downRequests;
	}

	public void move() {
		System.out.println(getDownRequests());
		System.out.println(getUpRequests());
		Direction nearestEnd = nearestEnd();
		moveToNearestEnd(nearestEnd);
		moveToOppsiteDirection(nearestEnd);
	}

	/**
	 * Indicates the nearest direction to go
	 * 
	 * @return
	 */
	private Direction nearestEnd() {
		Integer currentPosition = elevator.getCurrentPosition();
		Integer downDirection = currentPosition - 0;
		Integer upDirection = totalFloors - currentPosition;
		int compare = Integer.compare(downDirection, upDirection);
		if (compare <= 0) {
			System.out.println("The elevator is going down...");
			return Direction.DOWN;
		} else {
			System.out.println("The elevator is going up...");
			return Direction.UP;
		}
	}

	/**
	 * Move the elevator towards the informed direction, removing the attended
	 * floors from the request list
	 * 
	 * @param nearestEnd The direction that the elevator is moving
	 */
	private void moveToNearestEnd(Direction nearestEnd) {
		String msgMoving = String.format("Now moving %s", nearestEnd.toString());
		System.out.println(msgMoving);
		List<Integer> temp = Direction.UP.equals(nearestEnd) ? getUpRequests() : getDownRequests();
		if (Direction.UP.equals(nearestEnd)) {
			moveUp(temp);
		} else {
			moveDown(temp);
		}
	}

	private void moveDown(List<Integer> list) {
		ListIterator<Integer> iterator = list.listIterator(list.size());
		while (iterator.hasPrevious()) {
			Integer nextFloor = iterator.previous();
			getElevator().setCurrentPosition(nextFloor);
			String msgReaching = String.format("Reaching %s floor", nextFloor);
			System.out.println(msgReaching);
			iterator.remove();
		}
		getDownRequests().clear();
	}

	private void moveUp(List<Integer> list) {
		ListIterator<Integer> iterator = list.listIterator();
		while (iterator.hasNext()) {
			Integer nextFloor = iterator.next();
			getElevator().setCurrentPosition(nextFloor);
			String msgReaching = String.format("Reaching %s floor", nextFloor);
			System.out.println(msgReaching);
			iterator.remove();
		}
		getUpRequests().clear();
	}

	/**
	 * Move the elevator towards the opposite direction, attending the most outer
	 * requests
	 * 
	 * @param nearestEnd
	 */
	private void moveToOppsiteDirection(Direction nearestEnd) {
		List<Integer> temp = Direction.UP.equals(nearestEnd) ? getDownRequests() : getUpRequests();
		ListIterator<Integer> iterator = temp.listIterator(temp.size());
		while (iterator.hasPrevious()) {
			Integer nextFloor = iterator.previous();
			String msgReaching = String.format("Reaching %s floor", nextFloor);
			System.out.println(msgReaching);
			getElevator().setCurrentPosition(nextFloor);
		}
		
		if (Direction.UP.equals(nearestEnd)) {
			getDownRequests().clear();
		} else {
			getUpRequests().clear();
		}
	}

	public void handleRequests(Person person) {
		Integer currentPosition = this.getElevator().getCurrentPosition();
		Integer initialFloor = person.getInitialFloor();
		
		if (initialFloor.compareTo(currentPosition) < 0) {
			getDownRequests().add(initialFloor);
		} else if (initialFloor.compareTo(currentPosition) > 0) {
			getUpRequests().add(initialFloor);
		}
		
		Integer destinationFloor = person.getDestinationFloor();
		if (destinationFloor.compareTo(currentPosition) < 0) {
			getDownRequests().add(destinationFloor);
		} else if (destinationFloor.compareTo(currentPosition) > 0) {
			getUpRequests().add(destinationFloor);
		}
	}

}
