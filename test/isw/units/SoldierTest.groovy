package isw.units;

import groovy.util.GroovyTestCase;

class SoldierTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	def checkBaseHealthDoesntChange(soldier, originalBaseHealth) {
		assertEquals(soldier.baseHealth, originalBaseHealth)
	}
	
	public final void testReceiveHit() {
		def soldier = new Soldier()
		def originalBaseHealth = soldier.baseHealth
		
		//
		soldier.receiveHit(10)
		//
		
		assertEquals(soldier.health, 90)
		checkBaseHealthDoesntChange(soldier, originalBaseHealth)
	}
	
}
