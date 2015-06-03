package fr.utbm.info.vi51.marines.formation;

import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.util.LocalizedString;

public class CircleFormation extends Formation{
public static final float INTER_SLOT_SPACE = 50f;
	
	/**
	 */
	public CircleFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public CircleFormation(int slotCount) {
		scale(slotCount);
	}
	
	/** {@inheritDoc}
	 */
	@Override
	public String toString() {
		return LocalizedString.getString(BodyGuardFormation.class, "NAME", getSlots().size()); //$NON-NLS-1$
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
			
			float angle = MathUtil.PI *2f / spotCount;
			angle = (spotIndex - 1) * angle;
			Vector2f v = Vector2f.toOrientationVector(angle);
			v.scale(INTER_SLOT_SPACE);
			newSpot = new FormationSlot(v.getX(), v.getY(), angle/spotCount, getSlotAt(spotIndex-1), spotIndex);
		}
		
		return newSpot;
	}
}
