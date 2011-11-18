package isw.weapons;

import groovy.util.GroovyTestCase;
import isw.units.Unit;

import static org.junit.Assert.*;
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
	public final void fire() {
		
		def hitpointsreceived = 0
		
		def unit = [receiveHit: { hp -> hitpointsreceived = hp}] as Unit
		def laser = new Laser()
		
		//
		laser.fire(unit)
		//
		
		assertTrue("A weapon can not cause more hitpoints than " + 
				   "the ones it has", hitpointsreceived <= laser.hitPoints)
	}
	
	@Test
	public final void fireUnlockedTarget() {
		
		def hitpointsreceived = 0
		
		// Mocking using Map coercion
		// http://groovy.codehaus.org/Groovy+Mocks
		def unit = [receiveHit: { hp -> hitpointsreceived = hp}] as Unit
		def laser = new Laser()
		
		//
		laser.fire(unit)
		//
		
		def hitPoints = laser.hitPoints
		def penaltyFactor = 0.18 // laser implementation
		def expected = hitPoints * (1 - penaltyFactor)
		
		assertEquals("A penalty factor is applied when firing to unlocked targets.",
					 expected, hitpointsreceived, 0)
	}

	@Test
	public final void fireLockedTarget() {
		
		def hitpointsreceived = 0
		
		// Mocking using MockFor and StubFor
		// http://groovy.codehaus.org/Groovy+Mocks
		def mockContextClass = new MockFor(Unit)
		mockContextClass.ignore.invokeMethod("equals", null)
		mockContextClass.demand.receiveHit{ hp -> hitpointsreceived = hp}
		mockContextClass.use {
			def unit = new Unit()
			def laser = new Laser()
			//
			laser.lock(unit)
			laser.fire(unit)
			//

			def hitPoints = laser.hitPoints
			def expected = hitPoints // no penalties applied
			
			assertEquals("Non penalty factor is applied when firing to locked targets.",
						 expected, hitpointsreceived, 0)
		}
	}

	/*
	* Once a target is locked, we can use Fire without specifying
	* the target. The target shooting would be the target already
	* locked.
	*/
	@Test
	public final void fireWithoutTargetLockedTarget() {
		
		def methodCalled = false
		
		def unit = [receiveHit: { hp -> methodCalled = true }] as Unit
		def laser = new Laser()
		
		//
		laser.lock(unit)
		laser.fire()
		//
		
		assertTrue("If no target is specified, the locked target IS the target", methodCalled)
	}
	
	@Test
	public final void fireLockedTargetThenRelease() {

		def u1 = new Unit()
		
		def mockContextClass = new MockFor(Unit)
		mockContextClass.ignore.invokeMethod("equals", null)
		mockContextClass.demand.receiveHit(1){ hp -> }
		mockContextClass.use {
			def unit = new Unit()
			def laser = new Laser()
			
			//
			laser.lock(unit)
			laser.fire() // Fire locked target, then unlocked it
			laser.fire() // No target at this point, so receiveHit
						 // should be called just one time
			//
		}
	}
}
