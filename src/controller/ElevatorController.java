package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import enumeration.Direction;
import model.Elevator;

public class ElevatorController {

	private Elevator elevator;
	private Integer totalFloors;
	List<Integer> upRequests = new ArrayList<Integer>();
	List<Integer> downRequests = new ArrayList<Integer>();
	List<Integer> repeatedRequests = new ArrayList<Integer>();

	public ElevatorController(Elevator elevator, Integer totalFloors) {
		super();
		this.elevator = elevator;
		this.totalFloors = totalFloors;
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

	public List<Integer> getRepeatedRequests() {
		return repeatedRequests;
	}

	public void setRepeatedRequests(List<Integer> repeatedRequests) {
		this.repeatedRequests = repeatedRequests;
	}

	/**
	 * Make the elevator move
	 */
	public void move() {
		Direction nearestEnd = nearestEnd();
		moveToNearestEnd(nearestEnd);
		moveToOppsiteEnd(nearestEnd);
		if (!getRepeatedRequests().isEmpty())
			handleRepeatedRequests();
	}

	/**
	 * Indicates the nearest direction to go
	 * 
	 * @return A Direction indication where to go
	 */
	private Direction nearestEnd() {
		Integer currentPosition = elevator.getCurrentPosition();
		Integer upDirection = totalFloors - currentPosition;
		int compare = Integer.compare(currentPosition, upDirection);
		if (compare <= 0) {
			getElevator().printInformation("The elevator is going down...");
			return Direction.DOWN;
		} else {
			getElevator().printInformation("The elevator is going up...");
			return Direction.UP;
		}
	}

	/**
	 * Move the elevator towards the informed direction, removing the attended
	 * floors from the request list
	 * 
	 * @param nearestEnd
	 *            The direction that the elevator is moving
	 */
	private void moveToNearestEnd(Direction nearestEnd) {
		if (Direction.UP.equals(nearestEnd)) {
			moveForward(getUpRequests());
		} else {
			moveBackward(getDownRequests());
		}
		clearRequest(nearestEnd);
	}

	/**
	 * Move the elevator towards the opposite direction, attending the most
	 * outer requests
	 * 
	 * @param nearestEnd
	 * @throws Exception
	 */
	private void moveToOppsiteEnd(Direction nearestEnd) {

		if (Direction.UP.equals(nearestEnd)) {
			getElevator().printInformation("The elevator is going down...");
			moveBackward(getDownRequests());
			clearRequest(Direction.DOWN);
		} else if (Direction.DOWN.equals(nearestEnd)) {
			getElevator().printInformation("The elevator is going up...");
			moveBackward(getUpRequests());
			clearRequest(Direction.UP);
		}
	}

	/**
	 * Move the elevator backward
	 * 
	 * @param list
	 */
	private void moveBackward(List<Integer> list) {
		ListIterator<Integer> iterator = list.listIterator(list.size());
		while (iterator.hasPrevious()) {
			Integer nextFloor = iterator.previous();
			getElevator().setCurrentPosition(nextFloor);
			String msgReaching = String.format("Reaching %s floor", nextFloor);
			getElevator().printInformation(msgReaching);
			iterator.remove();
		}
	}

	/**
	 * Move the elevator forward
	 * 
	 * @param list
	 *            The request list to be
	 */
	private void moveForward(List<Integer> list) {
		ListIterator<Integer> iterator = list.listIterator();
		while (iterator.hasNext()) {
			Integer nextFloor = iterator.next();
			getElevator().setCurrentPosition(nextFloor);
			String msgReaching = String.format("Reaching %s floor", nextFloor);
			getElevator().printInformation(msgReaching);
			iterator.remove();
		}
	}

	/**
	 * Method that distributes requests from a Person into the requests lists
	 * 
	 * @param person
	 *            A Person with their requests
	 */
	public void handleRequests(Integer originFloor, Integer destinationFloor) {
		if (getUpRequests().contains(originFloor) || getDownRequests().contains(originFloor)) {
			getRepeatedRequests().add(originFloor);
		} else {
			distributeRequests(originFloor);
		}

		if (getUpRequests().contains(destinationFloor) || getDownRequests().contains(destinationFloor)) {
			getRepeatedRequests().add(originFloor);
		} else {
			distributeRequests(destinationFloor);
		}

		Collections.sort(getDownRequests());
		Collections.sort(getUpRequests());
	}

	public void handleRepeatedRequests() {
		for (Integer floor : getRepeatedRequests()) {
			distributeRequests(floor);
		}
		getRepeatedRequests().clear();
		this.move();
	}

	/**
	 * 
	 * @param floor
	 * @param currentFloor
	 */
	private void distributeRequests(Integer floor) {
		Integer currentFloor = this.getElevator().getCurrentPosition();
		if (floor.compareTo(currentFloor) < 0) {
			getDownRequests().add(floor);
		} else if (floor.compareTo(currentFloor) > 0) {
			getUpRequests().add(floor);
		}
	}

	/**
	 * Clear the request list
	 * 
	 * @param direction
	 *            The Direction to be cleared
	 */
	public void clearRequest(Direction direction) {
		if (Direction.UP.equals(direction)) {
			getUpRequests().clear();
		} else {
			getDownRequests().clear();
		}
	}
}
