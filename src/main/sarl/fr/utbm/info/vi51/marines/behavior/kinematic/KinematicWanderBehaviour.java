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
package fr.utbm.info.vi51.marines.behavior.kinematic;

import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.behavior.WanderBehaviour;

/**
 * Kinematic Wander Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class KinematicWanderBehaviour implements WanderBehaviour {

	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runWander(Point2f position, Vector2f orientation, 
			float linearSpeed, float maxLinearSpeed, 
			float angularSpeed, float maxAngularSpeed) {
		BehaviourOutput output = new BehaviourOutput(DynamicType.KINEMATIC);
		
		Vector2f v = new Vector2f(orientation);
		if (v.length()>0) v.normalize();
		v.scale(maxLinearSpeed);
		
		output.setLinear(v.getX(), v.getY());
		
		output.setAngular((float) (Math.random() - Math.random()) * maxAngularSpeed);
		
		return output;
	}
	
}