/* 
 * $Id$
 * 
 * Copyright (C) 2007-2011 Stephane Galland <stephane.galland@utbm.fr>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.utbm.info.vi51.marines.formation;

import fr.utbm.info.vi51.framework.util.LocalizedString;

/**
 * Formation in V.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class VFormation extends Formation {

	/** Space between slots.
	 */
	public static final float INTER_SLOT_SPACE = 70;
	
	/**
	 */
	public VFormation() {
		scale(1);
	}
	
	/**
	 * @param slotCount is the count of slots in the formation.
	 */
	public VFormation(int slotCount) {
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
				newSpot = new FormationSlot(-INTER_SLOT_SPACE,-INTER_SLOT_SPACE,0,parent,spotIndex);
			}
			else {
				// right spot
				newSpot = new FormationSlot(-INTER_SLOT_SPACE,INTER_SLOT_SPACE,0,parent,spotIndex);
			}
		}
		
		return newSpot;
	}

}