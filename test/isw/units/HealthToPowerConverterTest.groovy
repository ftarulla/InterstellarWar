package isw.units;

import groovy.util.GroovyTestCase;

import isw.units.components.*

class HealthToPowerConverterTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testPowerNeverLowerZero() {

		def powerConverter = new BasicHealthToPowerConverter()
		def healthpoints = 0
		
		//
		def powerPoints = powerConverter.convert(healthpoints)
		//
		
		assertEquals("Power result from a converter shouldn't be lower than zero",
					  0, powerPoints)
	}
}
