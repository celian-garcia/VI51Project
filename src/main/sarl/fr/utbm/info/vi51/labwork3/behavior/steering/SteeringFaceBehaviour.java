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
package fr.utbm.info.vi51.labwork3.behavior.steering;

import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.labwork3.behavior.FaceBehaviour;

/**
 * Steering Face Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class SteeringFaceBehaviour implements FaceBehaviour {

	private final float ignoreDistance;
	private final SteeringAlignBehaviour alignBehaviour;

	/**
	 * @param ignoreDistance is the distance to the target under which the face behaviour is disable.
	 * @param alignBehaviour is the align behaviour to use to face to.
	 */
	public SteeringFaceBehaviour(float ignoreDistance, SteeringAlignBehaviour alignBehaviour) {
		this.ignoreDistance = Math.abs(ignoreDistance);
		this.alignBehaviour = alignBehaviour;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runFace(Point2f position, Vector2f orientation, float angularSpeed, float maxAngularAcc, Point2f target) {
		Vector2f alignTarget = new Vector2f();
		alignTarget.sub(target, position);
		if (alignTarget.length()<this.ignoreDistance) 
			return this.alignBehaviour.runAlign(orientation, angularSpeed, maxAngularAcc, orientation);
		return this.alignBehaviour.runAlign(orientation, angularSpeed, maxAngularAcc, alignTarget);
	}
	
}