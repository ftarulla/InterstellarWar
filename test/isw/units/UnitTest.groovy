package isw.units;

import groovy.mock.interceptor.MockFor
import groovy.util.GroovyTestCase;

import isw.units.components.*

class UnitTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	def checkBaseHealthDoesntChange(unit, originalBaseHealth) {
		assertEquals(unit.baseHealth, originalBaseHealth)
	}
	
	public final void testReceiveHit() {
		def unit = new Unit()
		def originalBaseHealth = unit.baseHealth
		
		// 1st hit
		def hitpoints = 10
		def expected = 90
		//
		unit.receiveHit(hitpoints)
		//
		assertEquals(expected, unit.health)
		checkBaseHealthDoesntChange(unit, originalBaseHealth)

		
		// 2nd hit
		hitpoints = 20
		expected = 70
		//
		unit.receiveHit(hitpoints)
		//
		assertEquals(expected, unit.health)
		checkBaseHealthDoesntChange(unit, originalBaseHealth)
	}
	
	public final void testHealthNeverLowerZero() {
		def unit = new Unit()
		
		def hitpoints = unit.baseHealth + 1
		
		//
		unit.receiveHit(hitpoints)
		//
		
		assertTrue("Units' health shouldn't be lower than zero", unit.health >= 0)
	}
	
	public final void testHealNeverUpperBaseHealth() {
		def unit = new Unit()
		
		def hitpoints = unit.baseHealth + 20
		unit.receiveHit(hitpoints)
		// here the unit health is zero
		// this fact is tested in other test
		
		def repairpoints = unit.baseHealth + 1
		//		
		unit.receiveRepair(repairpoints)
		//
		
		assertTrue("Units' health shouldn't be more than their base health", 
					 unit.health <= unit.baseHealth)
	}

	public final void testUnitDefaultPowerConverter() {
		def unit = new Unit()
		def stat = 0
		
		//
		try {
			stat = unit.powerStatus
		} catch (NullPointerException) {
			fail("Unit can not compute power status.")
		}
		//
		
		assertEquals("Units should have a null power converter by default that returns zero",
					  0, stat)
	}

	public final void testPowerConverterInstallation() {
		def unit = new Unit()
		
		def expected = 1000
		def mockContextClass = new MockFor(BasicHealthToPowerConverter)
		mockContextClass.demand.setMaxPower { p ->  }
		mockContextClass.demand.convert { hp -> expected }
		
		mockContextClass.use {
			def powerconverter = new BasicHealthToPowerConverter()
			unit.installHealthPowerConverter(powerconverter)
			
			def stat = unit.powerStatus
			assertEquals(expected, stat)
		}
	}
	
	public final void testPowerConverterUninstall() {
		def unit = new Unit()
		def expected = 0
		def stat = 0
		
		// mock for power converter
		expected = 1000
		def converter = [fromHealthToPower:{ hp -> expected }] as BasicHealthToPowerConverter
		//
		unit.installHealthPowerConverter(converter)
		stat = unit.powerStatus
		assertEquals(expected, stat)
		
		//
		unit.uninstallHealthPowerConverter()
		//
		expected = 0
		try {
			stat = unit.powerStatus
		} catch (NullPointerException) {
			fail("Unit can not compute power status.")
		}
	
		assertEquals("Units should always have a null power converter that returns zero " + 
					 "in case the converter is removed. ", expected, stat)
		
	}
	
}

