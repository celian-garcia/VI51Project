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
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.behavior.WanderBehaviour;

/**
 * Steering Wander Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class SteeringWanderBehaviour implements WanderBehaviour {

	private final SteeringFaceBehaviour faceBehaviour;
	private final float circleDistance;
	private final float circleRadius;
	private final float maxRotation;
	private float rotation;
	
	/**
	 * @param circleDistance is the distance between the entity and the circle center.
	 * @param circleRadius is the radius of the circle.
	 * @param maxRotation is the maximal rotation of the entity.
	 * @param faceBehaviour is the face behaviour to use to face the target point on the circle.
	 */
	public SteeringWanderBehaviour(float circleDistance, float circleRadius, float maxRotation, SteeringFaceBehaviour faceBehaviour) {
		this.circleDistance = circleDistance;
		this.circleRadius = circleRadius;
		this.maxRotation = maxRotation;
		this.rotation = 0f;
		this.faceBehaviour = faceBehaviour;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runWander(Point2f position, Vector2f orientation, float linearSpeed, float maxLinearAcc, float angularSpeed, float maxAngularAcc) {
		Vector2f circleDirection = new Vector2f(orientation);
		circleDirection.scale(this.circleDistance);
		
		Point2f circleCenter = new Point2f();
		circleCenter.add(position, circleDirection);

		float deltaAngle = (float) (Math.random() - Math.random()) * this.maxRotation;
		this.rotation = MathUtil.clamp(this.rotation+deltaAngle, -MathUtil.PI, MathUtil.PI);
		
		Vector2f targetDirection = new Vector2f(orientation);
		targetDirection.normalize();
		targetDirection.scale(this.circleRadius);
		targetDirection.turn(this.rotation);
		
		Point2f faceTarget = new Point2f();
		faceTarget.add(circleCenter, targetDirection);
				
		BehaviourOutput output = this.faceBehaviour.runFace(position, orientation, angularSpeed, maxAngularAcc, faceTarget);
		
		if (output==null) output = new BehaviourOutput(DynamicType.STEERING);
		
		targetDirection = new Vector2f(orientation);
		targetDirection.normalize();
		targetDirection.scale(maxLinearAcc);
		
		output.setLinear(targetDirection.getX(), targetDirection.getY());
		
		return output;
	}
	
}