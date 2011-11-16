package isw.units.components

class BasicHealthToPowerConverter extends HealthToPowerConverter {

	BasicHealthToPowerConverter() {
		maxPower = 1000
	}
	
	@Override
	def convert(healthpoints) {
		// utilizo healthpoints como un porcentaje
		// TODO: refactor with NullHealth...
		return maxPower * (healthpoints/100)
	}
}
