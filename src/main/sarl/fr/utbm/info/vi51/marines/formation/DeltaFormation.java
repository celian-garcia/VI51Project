package fr.utbm.info.vi51.marines.formation;

import fr.utbm.info.vi51.framework.util.LocalizedString;

public class DeltaFormation extends Formation{
	/** Space between slots.
	 */
	public static final float INTER_SLOT_SPACE = 50f;
	
	/**
	 */
	public DeltaFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public DeltaFormation(int slotCount) {
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
			FormationSlot parent = (spotIndex<=2) ? getSlotAt(0) : getSlotAt(spotIndex-2);
			
			if (spotIndex%2==0) {
				// left spot
				newSpot = new FormationSlot(INTER_SLOT_SPACE,-INTER_SLOT_SPACE,0,parent,spotIndex);
			}
			else {
				// right spot
				newSpot = new FormationSlot(INTER_SLOT_SPACE,INTER_SLOT_SPACE,0,parent,spotIndex);
			}
		}
		
		return newSpot;
	}
}
