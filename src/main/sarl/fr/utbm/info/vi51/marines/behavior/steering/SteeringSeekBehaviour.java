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
package fr.utbm.info.vi51.marines.behavior.steering;

import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.behavior.SeekBehaviour;

/**
 * Steering Seek Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class SteeringSeekBehaviour implements SeekBehaviour {

	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runSeek(Point2f position, float linearSpeed, float maxLinearAcc, Point2f target) {
		BehaviourOutput output = new BehaviourOutput(DynamicType.STEERING);
		
		Vector2f direction = new Vector2f();
		direction.sub(target,position);
		
		direction.normalize();		
		direction.scale(maxLinearAcc);
		
		output.setLinear(direction.getX(), direction.getY());
		
		return output;
	}
	
}