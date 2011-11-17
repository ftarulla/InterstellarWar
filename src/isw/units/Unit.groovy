package isw.units

import isw.units.components.NullHealthToPowerConverter

class Unit {
	
	def baseHealth = 100
	def hitPoints = 0
	private powerConverter = null	
	
	public Unit() {
		powerConverter = new NullHealthToPowerConverter()
	}

	def getHealth() {
		return baseHealth - hitPoints
	}
	
	def receiveHit(hitPoints) {
		this.hitPoints += hitPoints
		this.hitPoints = Math.min(this.hitPoints, baseHealth)
	}

	def receiveRepair(repairPoints) {
		this.hitPoints -= repairPoints
		this.hitPoints = Math.max(this.hitPoints, 0)
	}
	
	def getPowerStatus() {
		powerConverter.convert(health)
	}

	def installHealthPowerConverter(powerConverter) {
		this.powerConverter = powerConverter
	}
	
	def uninstallHealthPowerConverter() {
		this.powerConverter = new NullHealthToPowerConverter()
	}

}
