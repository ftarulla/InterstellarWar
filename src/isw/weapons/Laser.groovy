package isw.weapons

class Laser {

	def hitPoints = 10
	
	public Laser() {

	}

	def shoot(target) {
		target.receiveHit(hitPoints)
	}

}
