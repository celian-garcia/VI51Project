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


/** Permits to agents to obtain a free slot in a formation.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class FormationAssignment {

	private int nextFreeSlot = 1;
	private int mCount;
	private boolean haveLeader = false;

	/**
	 */
	public FormationAssignment(int count) {
		mCount = count;
	}
	
	/** Allocate the next free slot.
	 * 
	 * @param isLeader indicates if the caller is the leader or a follower.
	 * @return the index of the next free slot.
	 */
	public synchronized int allocate(boolean isLeader) {
		if (isLeader) {
			haveLeader = true;
			return 0;
		}
		
		if (nextFreeSlot < mCount) {
			return this.nextFreeSlot++;
		}
		
		return -1;
	}
	
	public synchronized boolean haveLeader(){
		return haveLeader;
	}

}