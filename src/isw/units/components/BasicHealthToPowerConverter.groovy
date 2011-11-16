package isw.units.components

class BasicHealthToPowerConverter extends HealthToPowerConverter {

	BasicHealthToPowerConverter() {
		
	}
	
	@Override
	def convert(healthpoints) {
		// utilizo healthpoints como un porcentaje
		// TODO: refactor with NullHealth...
		return 1000 * (healthpoints/100)
	}
}
