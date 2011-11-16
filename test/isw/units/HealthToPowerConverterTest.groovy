package isw.units;

import isw.units.components.*

import org.junit.Test;
import groovy.util.GroovyTestCase;

class HealthToPowerConverterTest extends GroovyTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testPowerNeverLowerZero() {

		def powerConverter
		def powerPoints
		def healthpoints = 1
		
		//
		powerConverter = new NullHealthToPowerConverter()
		powerPoints = powerConverter.convert(healthpoints)
		//
		assertTrue("Power result from a converter shouldn't be lower than zero", powerPoints >= 0)
		
		//
		powerConverter = new BasicHealthToPowerConverter()
		powerPoints = powerConverter.convert(healthpoints)
		//
		assertTrue("Power result from a converter shouldn't be lower than zero", powerPoints >= 0)
		
	}
}
