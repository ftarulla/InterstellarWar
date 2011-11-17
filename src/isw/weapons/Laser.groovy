package isw.weapons

import isw.units.NullUnit

class Laser {

	def hitPoints = 20
	private def lockedTarget = new NullUnit()
	
	public Laser() {

	}

	def lock(target) {
		lockedTarget = target
	}
	
	def unlock(){
		lockedTarget = new NullUnit()
	}
	
	def shoot() {
		shoot(lockedTarget)
	}
	
	def shoot(target) {
		
		def penalty = 0
		
		if (!lockedTarget.equals(target)) {
			penalty = 15 * hitPoints / 100
		}
		
		target.receiveHit(hitPoints - penalty)
	}

}
