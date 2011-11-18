package isw.units;

import groovy.mock.interceptor.MockFor
import groovy.util.GroovyTestCase;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import isw.units.components.*

class UnitTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	def checkBaseHealthDoesntChange(unit, originalBaseHealth) {
		assertEquals(unit.baseHealth, originalBaseHealth)
	}
	
	@Test
	public final void receiveHit() {
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
	
	@Test
	public final void healthNeverLowerZero() {
		def unit = new Unit()
		
		def hitpoints = unit.baseHealth + 1
		
		//
		unit.receiveHit(hitpoints)
		//
		
		assertTrue("Units' health shouldn't be lower than zero", unit.health >= 0)
	}
	
	@Test
	public final void healNeverUpperBaseHealth() {
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

	@Test
	public final void unitDefaultPowerConverter() {
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

	@Test
	public final void powerConverterInstallation() {
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
	
	@Test
	public final void powerConverterUninstall() {
		def unit = new Unit()
		def expected = 0
		def stat = 0
		
		// mock for power converter
		expected = 1000
		def converter = [convert:{ hp -> expected }] as BasicHealthToPowerConverter
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

