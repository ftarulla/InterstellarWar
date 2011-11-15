package isw.units

class Robot {
	
	def baseHealth = 100
	def hitPoints = 0
	
	Robot() {
	}

	def getHealth() {
		return baseHealth - hitPoints
	}
	
	def receiveHit(hitPoints) {
		this.hitPoints += hitPoints
		this.hitPoints = Math.min(this.hitPoints, baseHealth)
	}
	

}