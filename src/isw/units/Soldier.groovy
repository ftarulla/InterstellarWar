package isw.units

class Soldier {
	
	def baseHealth = 100
	def hitPoints = 0
	
	Soldier() {
	}

	def getHealth() {
		return baseHealth - hitPoints
	}
	
	def receiveHit(hitPoints) {
		this.hitPoints += hitPoints 
	}
}
