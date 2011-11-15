package isw.units

class Soldier {
	
	def health = 100
	
	Soldier() {
		
	}
	
	def receiveHit(hitPoints) {
		health -= hitPoints 
	}
}
