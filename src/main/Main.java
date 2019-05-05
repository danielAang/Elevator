package main;

import controller.ElevatorController;
import model.Elevator;
import model.Person;

public class Main {

	public static void main(String[] args) {
//		Elevator elevator = new Elevator(5);
//		ElevatorController controller = new ElevatorController(elevator);
//		
//		Person person1 = new Person(8, 0);
//		Person person2 = new Person(0, 10);
//		controller.handleRequests(person1);
//		controller.handleRequests(person2);
//		controller.move();
		
		Elevator elevator = new Elevator(3);
		ElevatorController controller = new ElevatorController(elevator);
		Person person3 = new Person(8, 0);
		Person person4 = new Person(0, 7);
		Person person5 = new Person(3, 15);
		controller.handleRequests(person3);
		controller.handleRequests(person4);
		controller.handleRequests(person5);
		controller.move();
	}

}
