package fr.utbm.info.vi51.marines.formation;

import fr.utbm.info.vi51.framework.util.LocalizedString;

public class CrossFormation extends Formation{
public static final float INTER_SLOT_SPACE = 50f;
	
	/**
	 */
	public CrossFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public CrossFormation(int slotCount) {
		scale(slotCount);
	}
	
	/** {@inheritDoc}
	 */
	@Override
	public String toString() {
		return LocalizedString.getString(VFormation.class, "NAME", getSlots().size()); //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected synchronized FormationSlot createSpot(int spotIndex, int spotCount) {
		FormationSlot newSpot;
		
		if (spotIndex==0) {
			newSpot = new FormationSlot(); // leader
		}
		else {
			FormationSlot parent = getSlotAt(0);
			
			if (spotIndex%4==1) {
				// bottom-right spot
				newSpot = new FormationSlot(INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),-INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),0,parent,spotIndex);
			}
			else if(spotIndex%4==2){
				// top-right spot
				newSpot = new FormationSlot(INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),0,parent,spotIndex);
			}
			else if(spotIndex%4==3){
				//bottom-left spot
				newSpot = new FormationSlot(-INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),-INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),0,parent,spotIndex);
			}
			else{
				//top-left spot
				newSpot = new FormationSlot(-INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),INTER_SLOT_SPACE*(Math.floorDiv(spotIndex-1, 4)+1),0,parent,spotIndex);
			}
		}
		
		return newSpot;
	}
}
