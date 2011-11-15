package isw.units;

import groovy.util.GroovyTestCase;

class SoldierTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testReceiveHit() {
		def soldier = new Soldier()
		soldier.receiveHit(10)
		
		assertEquals(soldier.health, 90)
	}

}
