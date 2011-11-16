package isw.units;

import groovy.mock.interceptor.MockFor
import groovy.util.GroovyTestCase;

import isw.units.components.*

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
		
		def hitpoints = robot.baseHealth + 1
		def expected = 0
		
		//
		robot.receiveHit(hitpoints)
		//
		
		assertEquals("Robots' health shouldn't be lower than zero", expected, robot.health)
	}
	
	public final void testHealNeverUpperBaseHealth() {
		def robot = new Robot()
		
		def hitpoints = robot.baseHealth + 20
		robot.receiveHit(hitpoints)
		// here the robot health is zero
		// this fact is tested in other test
		
		def repairpoints = robot.baseHealth + 1
		//		
		robot.receiveRepair(repairpoints)
		//
		
		assertEquals("Robots' health shouldn't be more than their base health", 
					 robot.baseHealth, robot.health)
	}

	public final void testRobotDefaultPowerConverter() {
		def robot = new Robot()
		def stat = 0
		
		//
		try {
			stat = robot.powerStatus
		} catch (NullPointerException) {
			fail("Robot can not compute power status.")
		}
		//
		
		assertEquals("Robots should have a null power converter by default that returns zero",
					  0, stat)
	}

	public final void testPowerConverterInstallation() {
		def robot = new Robot()
		
		def expected = 1000
		def mockContextClass = new MockFor(BasicHealthToPowerConverter)
		mockContextClass.demand.setMaxPower { p ->  }
		mockContextClass.demand.convert { hp -> expected }
		
		mockContextClass.use {
			def powerconverter = new BasicHealthToPowerConverter()
			robot.installHealthPowerConverter(powerconverter)
			
			def stat = robot.powerStatus
			assertEquals(expected, stat)
		}
	}
	
	public final void testPowerConverterUninstall() {
		def robot = new Robot()
		def expected = 0
		def stat = 0
		
		// mock for power converter
		expected = 1000
		def converter = [fromHealthToPower:{ hp -> expected }] as BasicHealthToPowerConverter
		//
		robot.installHealthPowerConverter(converter)
		stat = robot.powerStatus
		assertEquals(expected, stat)
		
		//
		robot.uninstallHealthPowerConverter()
		//
		expected = 0
		try {
			stat = robot.powerStatus
		} catch (NullPointerException) {
			fail("Robot can not compute power status.")
		}
	
		assertEquals("Robots should always have a null power converter that returns zero " + 
					 "in case the converter is removed. ", expected, stat)
		
	}
	
}

