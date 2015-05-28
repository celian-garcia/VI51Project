package fr.utbm.info.vi51.marines.formation;


import fr.utbm.info.vi51.framework.util.LocalizedString;

public class RectangleFormation extends Formation{
public static final float INTER_SLOT_SPACE = 50f;
	
	/**
	 */
	public RectangleFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public RectangleFormation(int slotCount) {
		scale(slotCount);
	}
	
	/** {@inheritDoc}
	 */
	@Override
	public String toString() {
		return LocalizedString.getString(RectangleFormation.class, "NAME", getSlots().size()); //$NON-NLS-1$
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
		else if((spotCount+1)%4 ==0){
			FormationSlot parent = getSlotAt(0);
			int nb = (spotCount+1)/4;
			if(spotIndex <= nb){
				newSpot = new FormationSlot(0,-INTER_SLOT_SPACE*spotIndex,0,parent,spotIndex);
			}
			else if(spotIndex<=nb*2){
				newSpot = new FormationSlot(INTER_SLOT_SPACE,0,0,getSlotAt(spotIndex-1),spotIndex);
			}
			else if(spotIndex<=nb*3){
				newSpot = new FormationSlot(0,INTER_SLOT_SPACE,0,getSlotAt(spotIndex-1),spotIndex);
			}
			else {
				newSpot = new FormationSlot(-INTER_SLOT_SPACE,0,0,getSlotAt(spotIndex-1),spotIndex);
			}

		}
		
		else{
			FormationSlot parent = getSlotAt(0);
			int nb = (spotCount+1)/4;
			if(spotIndex <= nb){
				newSpot = new FormationSlot(0,-INTER_SLOT_SPACE*spotIndex,0,parent,spotIndex);
			}
			else if(spotIndex<=nb*2+1){
				newSpot = new FormationSlot(INTER_SLOT_SPACE,0,0,getSlotAt(spotIndex-1),spotIndex);
			}
			else if(spotIndex<=nb*3+1){
				newSpot = new FormationSlot(0,INTER_SLOT_SPACE,0,getSlotAt(spotIndex-1),spotIndex);
			}
			else {
				newSpot = new FormationSlot(-INTER_SLOT_SPACE,0,0,getSlotAt(spotIndex-1),spotIndex);
			}
			
		}
		return newSpot;
	}
}

