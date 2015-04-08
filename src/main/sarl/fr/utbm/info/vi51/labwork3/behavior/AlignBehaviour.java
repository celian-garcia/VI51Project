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
package fr.utbm.info.vi51.labwork3.behavior;

import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.math.Vector2f;

/**
 * Alignment Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public interface AlignBehaviour {

	/**
	 * @param orientation is the current orientation of the entity.
	 * @param angularSpeed is the current angular speed of the entity.
	 * @param maxAngular is the maximal angular speed or acceleration (depending on getType()) of the entity.
	 * @param target is the vector to match.
	 * @return the behaviour output.
	 */
	public BehaviourOutput runAlign(Vector2f orientation, float angularSpeed, float maxAngular, Vector2f target);
	
}