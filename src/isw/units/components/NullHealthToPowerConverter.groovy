package isw.units.components

class NullHealthToPowerConverter extends HealthToPowerConverter {

	NullHealthToPowerConverter() {
		maxPower = 0
	}
	
	@Override
	def convert(Object healthpoints) {
		return maxPower
	}

}
