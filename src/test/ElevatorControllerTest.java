package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.ElevatorControllerCLook;
import enumeration.Direction;
import junit.framework.TestCase;
import model.Elevator;
import model.Person;

public class ElevatorControllerTest extends TestCase {

	Elevator elevator;
	ElevatorControllerCLook controller;
	Person person1, person2, person3;

	@Before
	public void setUp() throws Exception {
		this.elevator = new Elevator(3);
		this.controller = new ElevatorControllerCLook(this.elevator, 10);
		this.person1 = new Person(8, 0);
		this.person2 = new Person(0, 7);
		this.person3 = new Person(3, 15);
	}

	@Test
	public void testUpRequestsFilled() {
		this.controller.handleRequests(person1.getInitialFloor(), person1.getDestinationFloor());
		this.controller.handleRequests(person2.getInitialFloor(), person2.getDestinationFloor());
		this.controller.handleRequests(person3.getInitialFloor(), person3.getDestinationFloor());
		Assert.assertNotNull("Up requests list is not null", this.controller.getUpRequests());
	}
	
	@Test
	public void testMove() {
		this.controller.move();		
	}
	
	@Test
	public void testDownRequestsFilled() {
		this.controller.handleRequests(person1.getInitialFloor(), person1.getDestinationFloor());
		this.controller.handleRequests(person2.getInitialFloor(), person2.getDestinationFloor());
		this.controller.handleRequests(person3.getInitialFloor(), person3.getDestinationFloor());
		Assert.assertNotNull("Down requests list is not null", this.controller.getDownRequests());
	}

	@Test
	public void testClearUpRequests() {
		this.controller.handleRequests(person1.getInitialFloor(), person1.getDestinationFloor());
		this.controller.handleRequests(person2.getInitialFloor(), person2.getDestinationFloor());
		this.controller.handleRequests(person3.getInitialFloor(), person3.getDestinationFloor());
		this.controller.clearRequest(Direction.UP);
		boolean empty = this.controller.getUpRequests().isEmpty();
		assertTrue(empty);
	}
	
	@Test
	public void testClearDownRequests() {
		this.controller.handleRequests(person1.getInitialFloor(), person1.getDestinationFloor());
		this.controller.handleRequests(person2.getInitialFloor(), person2.getDestinationFloor());
		this.controller.handleRequests(person3.getInitialFloor(), person3.getDestinationFloor());
		this.controller.clearRequest(Direction.DOWN);
		boolean empty = this.controller.getDownRequests().isEmpty();
		System.out.println(this.controller.getDownRequests());
		assertTrue(empty);
	}
	
}
