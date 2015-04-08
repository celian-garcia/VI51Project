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
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.behavior.AlignBehaviour;

/**
 * Steering Align Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class SteeringAlignBehaviour implements AlignBehaviour {

	private final float stopRadius; 
	private final float slowRadius; 

	/**
	 * @param stopRadius is the angle between the current direction and the target direction
	 * under which the rotation for alignment is ignored.
	 * @param slowRadius is the angle between the current direction and the target direction
	 * under which the rotation is going slower.
	 */
	public SteeringAlignBehaviour(float stopRadius, float slowRadius) {
		this.stopRadius = stopRadius;
		this.slowRadius = slowRadius;
	}
	
	private void stop(BehaviourOutput output, float angularSpeed, float maxAngularAcc) {
		float acc = -Math.min(Math.abs(angularSpeed), maxAngularAcc);
		output.setAngular(Math.signum(angularSpeed)*acc);
	}
	
	private void brake(BehaviourOutput output, float angularSpeed, float maxAngularAcc, float rotation) {
		float t = angularSpeed / rotation; //this.timeToTarget;
		output.setAngular(-angularSpeed * t);
	}

	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runAlign(Vector2f orientation, float angularSpeed, float maxAngularAcc, Vector2f target) {
		BehaviourOutput output = new BehaviourOutput(DynamicType.STEERING);

		// Get the naive direction to target, with an angle representation
		float rotation = orientation.signedAngle(target);
		
		if (rotation!=0 && angularSpeed==0) {
			output.setAngular((float) Math.signum(rotation) * Math.min(maxAngularAcc,rotation));
		}
		else {
			// the result is already mapped to (-pi,pi) interval with signedAngle()		
			float rotationSize = Math.abs(rotation);
			
			// Check if we are there
			if (rotationSize<this.stopRadius) {
				// try to stop
				stop(output, angularSpeed, maxAngularAcc);
			}
			else if (rotationSize<this.slowRadius) {
				brake(output, angularSpeed, maxAngularAcc, rotationSize);
			}
			else {
				// Turn as fast as possible
				output.setAngular(Math.signum(rotation) * maxAngularAcc);
			}
		}
		
		return output;
	}
	
}