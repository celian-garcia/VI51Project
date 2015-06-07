package fr.utbm.info.vi51.marines.formation;


import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.util.LocalizedString;

public class LineFormation extends Formation{

public static final float INTER_SLOT_SPACE = 100f;
	
	/**
	 */
	public LineFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public LineFormation(int slotCount) {
		scale(slotCount);
	}
	
	/** {@inheritDoc}
	 */
	@Override
	public String toString() {
		return LocalizedString.getString(LineFormation.class, "NAME", getSlots().size()); //$NON-NLS-1$
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
			if (spotIndex%2==0){
				newSpot = new FormationSlot(0,-INTER_SLOT_SPACE*spotIndex/2,0,parent,spotIndex);
			}
			else{
				newSpot = new FormationSlot(0,INTER_SLOT_SPACE*(spotIndex+1)/2,0,parent,spotIndex);
			}
				
		}
		
		return newSpot;
	}


}
