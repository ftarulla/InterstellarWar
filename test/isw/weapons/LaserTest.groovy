package isw.weapons;

import static org.junit.Assert.*;
import isw.units.Unit;

import groovy.mock.interceptor.MockFor
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class LaserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testShoot() {
		
		def hitpointsreceived = 0
		
		def mockContextClass = new MockFor(Unit)
		mockContextClass.demand.receiveHit{ hp -> hitpointsreceived = hp}
		mockContextClass.use {
			def unit = new Unit()
			def laser = new Laser()
			//
			laser.shoot(unit)
			//
			
			assertTrue("A weapon can not cause more hitpoints than " + 
					   "the ones it has", hitpointsreceived <= laser.hitPoints)
		}
	}

}
