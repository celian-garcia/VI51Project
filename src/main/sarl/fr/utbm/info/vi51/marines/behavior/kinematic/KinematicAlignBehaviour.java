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
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.behavior.AlignBehaviour;

/**
 * Kinematic Align Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class KinematicAlignBehaviour implements AlignBehaviour {

	private final float stopRadius; 

	/**
	 * @param stopRadius is the angle between the current direction and the target direction
	 * under which the rotation for alignment is ignored.
	 */
	public KinematicAlignBehaviour(float stopRadius) {
		this.stopRadius = stopRadius;
	}
	
	private void stop(BehaviourOutput output, float angularSpeed, float maxAngularSpeed) {
		float acc = -Math.min(Math.abs(angularSpeed), maxAngularSpeed);
		output.setAngular(Math.signum(angularSpeed)*acc);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runAlign(Vector2f orientation, float angularSpeed, float maxAngularSpeed, Vector2f target) {
		BehaviourOutput output = new BehaviourOutput(DynamicType.KINEMATIC);

		// Get the naive direction to target, with an angle representation
		float rotation = orientation.signedAngle(target);
		
		if (rotation!=0 && angularSpeed==0) {
			output.setAngular((float) Math.signum(rotation) * Math.min(maxAngularSpeed,rotation));
		}
		else {
			// the result is already mapped to (-pi,pi) interval with signedAngle()		
			float rotationSize = Math.abs(rotation);
			
			// Check if we are there
			if (rotationSize<this.stopRadius) {
				// try to stop
				stop(output, angularSpeed, maxAngularSpeed);
			}
			else {
				// Turn as fast as possible
				output.setAngular(Math.signum(rotation) * maxAngularSpeed);
			}
		}
		
		return output;
	}
	
}