package fr.utbm.info.vi51.marines.formation;

import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.util.LocalizedString;

public class SurroundFormation extends Formation{
	public static final float INTER_SLOT_SPACE = 100f;
	
	public SurroundFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public SurroundFormation(int slotCount) {
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
		if(spotIndex==0){ 
			newSpot = new FormationSlot();
			}//leader
		else if((spotCount+3)%4 ==0){ //leader at the center and the others forming a square around it 
			int nb=Math.floorDiv(spotCount+3, 4);
			float angle = MathUtil.PI / nb / spotCount;
			angle = (spotIndex - 1) * angle;
			if(spotIndex ==1){
				newSpot = new FormationSlot(0,-INTER_SLOT_SPACE*(spotCount-1)/8,angle,getSlotAt(0),spotIndex);
				}
			else if(spotIndex <= nb-((spotCount-1)/8)){
				newSpot = new FormationSlot(INTER_SLOT_SPACE*(spotIndex-1),0,angle,getSlotAt(1),spotIndex);
			}
			else if(spotIndex<=nb*2-((spotCount-1)/8)-1){
				newSpot = new FormationSlot(0,INTER_SLOT_SPACE,angle,getSlotAt(spotIndex-1),spotIndex);
			}
			else if(spotIndex<=nb*3-((spotCount-1)/8)-2){
				newSpot = new FormationSlot(-INTER_SLOT_SPACE,0,angle,getSlotAt(spotIndex-1),spotIndex);
			}
			else if(spotIndex<=nb*4-((spotCount-1)/8)-3){
				newSpot = new FormationSlot(0,-INTER_SLOT_SPACE,angle,getSlotAt(spotIndex-1),spotIndex);
			}
			else newSpot= new FormationSlot(INTER_SLOT_SPACE,0,angle,getSlotAt(spotIndex-1),spotIndex);
			}
		
		else { //leader at the center and the others forming a circle around it
			FormationSlot parent = getSlotAt(0);
			float angle = MathUtil.PI * 2f / spotCount;
			angle = (spotIndex - 1) * angle;
			Vector2f v = Vector2f.toOrientationVector(angle);
			v.scale(INTER_SLOT_SPACE);
			newSpot = new FormationSlot(v.getX(), v.getY(), angle, parent, spotIndex);
			
		}
			
			
		
	return newSpot;
	}
}
			

		
//		
