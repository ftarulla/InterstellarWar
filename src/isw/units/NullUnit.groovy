package isw.units

import java.nio.channels.OverlappingFileLockException;

import isw.units.components.NullHealthToPowerConverter

class NullUnit extends Unit {

	public NullUnit() {
		super()
		baseHealth = 0
	}
	
	@Override
	def receiveHit(hitPoints) {
		return
	}

	@Override
	def receiveRepair(repairPoints) {
		return
	}

	@Override
	def installHealthPowerConverter(powerConverter) {
		return
	}

}
