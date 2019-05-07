package main;

import controller.ElevatorControllerScan;
import model.Elevator;
import model.Person;

public class Main {

	public static void main(String[] args) {
		question1();
		question2();
	}

	private static void question2() {
		System.out.println("-----------------QUESTION 2---------------");
		Elevator elevator = new Elevator(3);
		ElevatorControllerScan controller = new ElevatorControllerScan(elevator, 20);
		Person person1 = new Person(8, 0);
		Person person2 = new Person(0, 7);
		Person person3 = new Person(3, 15);
		controller.handleRequests(person1.getInitialFloor(), person1.getDestinationFloor());
		controller.handleRequests(person2.getInitialFloor(), person2.getDestinationFloor());
		controller.handleRequests(person3.getInitialFloor(), person3.getDestinationFloor());
		controller.move();
		System.out.println("-------------------------------------------");
	}

	private static void question1() {
		System.out.println("-----------------QUESTION 1---------------");
		Elevator elevator = new Elevator(5);
		ElevatorControllerScan controller = new ElevatorControllerScan(elevator, 10);
		
		Person person1 = new Person(8, 0);
		Person person2 = new Person(0, 10);
		controller.handleRequests(person1.getInitialFloor(), person1.getDestinationFloor());
		controller.handleRequests(person2.getInitialFloor(), person2.getDestinationFloor());
		controller.move();
		System.out.println("-------------------------------------------");
	}

}
