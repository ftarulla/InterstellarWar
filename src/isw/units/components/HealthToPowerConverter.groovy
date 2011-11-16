package isw.units.components

abstract class HealthToPowerConverter {

	protected def maxPower = -1
	
	abstract def convert(healthpoints)
	
}
