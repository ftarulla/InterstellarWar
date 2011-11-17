package isw.units.components

class BasicHealthToPowerConverter extends HealthToPowerConverter {

	BasicHealthToPowerConverter() {
		maxPower = 1000
	}
	
	@Override
	def convert(healthpoints) {
		return maxPower * (healthpoints/100)
	}
}
