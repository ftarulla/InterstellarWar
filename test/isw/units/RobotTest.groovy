package isw.units;

import groovy.util.GroovyTestCase;

class RobotTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	def checkBaseHealthDoesntChange(robot, originalBaseHealth) {
		assertEquals(robot.baseHealth, originalBaseHealth)
	}
	
	public final void testReceiveHit() {
		def robot = new Robot()
		def originalBaseHealth = robot.baseHealth
		
		def hitpoints = 10
		def expected = 90
		
		//
		robot.receiveHit(hitpoints)
		//
		
		assertEquals(expected, robot.health)
		checkBaseHealthDoesntChange(robot, originalBaseHealth)
	}
	
	public final void testHealthNeverLowerZero() {
		def robot = new Robot()
		
		def hitpoints = robot.health + 1
		def expected = 0
		
		//
		robot.receiveHit(hitpoints)
		//
		
		assertEquals("Robots' health shouldn't be lower than zero", 0, robot.health)
	}

}
