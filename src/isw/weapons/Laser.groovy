package isw.weapons

import isw.units.NullUnit

class Laser {

	private def lockedTarget = new NullUnit()
	
	private final hitPoints = 20
	private def penaltyFactor = 0.18 //penalty factor when shooting a not locked target
	
	public Laser() {

	}

	def getHitPoints() {
		return hitPoints
	}
	
	def lock(target) {
		lockedTarget = target
	}
	
	def unlock(){
		lockedTarget = new NullUnit()
	}
	
	def fire() {
		fire(lockedTarget)
	}
	
	def fire(target) {
		
		def penalty = 0
		
		if (!target.equals(lockedTarget)) {
			// TODO: the penalty factor should be
			// set by the target
			penalty = hitPoints * penaltyFactor 
		}
		
		// fire
		target.receiveHit(hitPoints - penalty)
		
		// and forget :P
		unlock()
	}

}
