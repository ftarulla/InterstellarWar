package isw.units.components

class NullHealthToPowerConverter extends HealthToPowerConverter {

	@Override
	def convert(Object healthpoints) {
		return 0
	}

}
