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
package fr.utbm.info.vi51.framework.environment;

import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.time.TimeManager;
import fr.utbm.info.vi51.framework.util.LocalizedString;

/**
 * Abstract implementation of an object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public abstract class AbstractMobileObject extends AbstractSituatedObject implements MobileObject {

	private static final long serialVersionUID = -2670464828720893140L;
	
	private final float maxLinearSpeed;
	private final float maxLinearAcceleration;
	private final float maxAngularSpeed;
	private final float maxAngularAcceleration;
	
	private float angle = 0;
	private float currentAngularSpeed = 0;
	private Vector2f linearMove = new Vector2f();

	/**
	 * @param shape the shape of the body, considering that it is centered at the (0,0) position.
	 * @param maxLinearSpeed is the maximal linear speed.
	 * @param maxLinearAcceleration is the maximal linear acceleration.
	 * @param maxAngularSpeed is the maximal angular speed.
	 * @param maxAngularAcceleration is the maximal angular acceleration.
	 */
	public AbstractMobileObject(Shape2f<?> shape, float maxLinearSpeed, float maxLinearAcceleration, float maxAngularSpeed, float maxAngularAcceleration) {
		super(shape);
		this.maxLinearSpeed = Math.abs(maxLinearSpeed);
		this.maxLinearAcceleration = Math.abs(maxLinearAcceleration);
		this.maxAngularAcceleration = Math.abs(maxAngularAcceleration);
		this.maxAngularSpeed = Math.abs(maxAngularSpeed);
	}
	
	@Override
	public AbstractMobileObject clone() {
		AbstractMobileObject clone = (AbstractMobileObject) super.clone();
		clone.linearMove = this.linearMove.clone();
		return clone;
	}

	/** {@inheritDoc}
	 */
	public float getAngle() {
		return this.angle;
	}
	
	/** {@inheritDoc}
	 */
	public Vector2f getDirection() {
		return Vector2f.toOrientationVector(this.angle);
	}

	/** Set the orientation of the object.
	 * 
	 * @param angle
	 */
	protected void setAngle(float angle) {
		this.angle = angle;
		this.currentAngularSpeed = 0;
	}

	/** Set the direction of the object.
	 * 
	 * @param dx
	 * @param dy
	 */
	protected void setDirection(float dx, float dy) {
		this.angle = new Vector2f(dx, dy).getOrientationAngle();
		this.currentAngularSpeed = 0;
	}

	/** {@inheritDoc}
	 */
	public float getMaxLinearSpeed() {
		return this.maxLinearSpeed;
	}

	/** {@inheritDoc}
	 */
	public float getMaxAngularSpeed() {
		return this.maxAngularSpeed;
	}

	/** {@inheritDoc}
	 */
	public float getMaxLinearAcceleration() {
		return this.maxLinearAcceleration;
	}

	/** {@inheritDoc}
	 */
	public float getMaxAngularAcceleration() {
		return this.maxAngularAcceleration;
	}
	
	/** {@inheritDoc}
	 */
	public float getCurrentAngularSpeed() {
		return this.currentAngularSpeed;
	}

	/** {@inheritDoc}
	 */
	public float getCurrentLinearSpeed() {
		return this.linearMove.length();
	}

	/** {@inheritDoc}
	 */
	public Vector2f getCurrentLinearMotion() {
		return new Vector2f(this.linearMove);
	}

	/** Rotate the object.
	 * 
	 * @param rotation is the real instant motion. 
	 * @param simulationDuration is the time during which the motion is applied.
	 */
	void rotate(float rotation, float simulationDuration) {
		if (Double.isNaN(rotation)) {
			System.err.println(LocalizedString.getString(getClass(), "INVALID_ROTATION", getName()));
		} else if (Double.isNaN(simulationDuration)) {
			System.err.println(LocalizedString.getString(getClass(), "INVALID_DURATION", getName()));
		} else {
			this.angle += rotation;
			this.currentAngularSpeed = rotation / simulationDuration;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.linearMove.set(0,0);
	}

	/** Move the situated object.
	 * 
	 * @param dx is the real instant motion. 
	 * @param dy is the real instant motion.
	 * @param simulationDuration is the time during which the motion is applied.
	 * @param worldWidth is the width of the world.
	 * @param worldHeight is the height of the world.
	 * @return the real motion.
	 */
	Vector2f move(float dx, float dy, float simulationDuration, float worldWidth, float worldHeight) {
		// Ensure that the motion in inside the bounds of the world.
		Vector2f r = new Vector2f(dx, dy);
		Shape2f<?> currentShape = getShape();
		Shape2f<?> targetShape = currentShape.translate(r);
		Rectangle2f targetBounds = targetShape.getBounds();

		if (targetBounds.getLower().getX() < 0) {
			float exceedingAmount = - targetBounds.getLower().getX();
			r.addX(exceedingAmount);
		} else if (targetBounds.getUpper().getX() > worldWidth) {
			float exceedingAmount = targetBounds.getUpper().getX() - worldWidth;
			r.subX(exceedingAmount);
		}
		
		if (targetBounds.getLower().getY() < 0) {
			float exceedingAmount = - targetBounds.getLower().getY();
			r.addY(exceedingAmount);
		} else if (targetBounds.getUpper().getY() > worldHeight) {
			float exceedingAmount = targetBounds.getUpper().getY() - worldHeight;
			r.subY(exceedingAmount);
		}

		// Update the position
		addPosition(r.getX(), r.getY());
		
		// Update dynamic properties
		if (simulationDuration>0) {
			this.linearMove.set(r.getX(), r.getY());
			float distance = this.linearMove.length();
			if (distance>0) {
				this.linearMove.normalize();
				this.linearMove.scale(distance/simulationDuration);
			}
		}
		else {
			this.linearMove.set(0,0);
		}

		return r;
	}

	private Vector2f scaleVector(Vector2f v, float length, TimeManager clock) {
		Vector2f v2 = new Vector2f(v);
		if (v2.length()>0) v2.normalize();
		v2.scale(clock.perSecond(length));
		return v2;
	}

	/** Compute a steering move according to the linear move and to
	 * the internal attributes of this object.
	 * 
	 * @param move is the requested motion.
	 * @param clock is the simulation time manager
	 * @return the linear instant motion.
	 */
	Vector2f computeSteeringTranslation(Vector2f move, TimeManager clock) {
		Vector2f m = new Vector2f();
		m.add(this.linearMove,move);
		float lSpeed = m.length();
		if (lSpeed<0) lSpeed = 0f;
		if (lSpeed>this.maxLinearSpeed) lSpeed = this.maxLinearSpeed;
		
		return scaleVector(m, lSpeed, clock);
	}

	/** Compute a kinematic move according to the linear move and to
	 * the internal attributes of this object.
	 * 
	 * @param move is the requested motion.
	 * @param clock is the simulation time manager
	 * @return the linear instant motion.
	 */
	Vector2f computeKinematicTranslation(Vector2f move, TimeManager clock) {
		float lSpeed = move.length();
		if (lSpeed<0) lSpeed = 0f;
		if (lSpeed>this.maxLinearSpeed) lSpeed = this.maxLinearSpeed;
		
		return scaleVector(move, lSpeed, clock);
	}

	/** Compute a kinematic move according to the angular move and to
	 * the internal attributes of this object.
	 * 
	 * @param move is the requested motion.
	 * @param clock is the simulation time manager
	 * @return the angular instant motion.
	 */
	float computeKinematicRotation(float move, TimeManager clock) {
		return clock.perSecond(MathUtil.clamp(move, -getMaxAngularSpeed(), getMaxAngularSpeed()));
	}

	/** Compute a steering move according to the angular move and to
	 * the internal attributes of this object.
	 * 
	 * @param move is the requested motion.
	 * @param clock is the simulation time manager
	 * @return the angular instant motion.
	 */
	float computeSteeringRotation(float move, TimeManager clock) {
		return clock.perSecond(MathUtil.clamp(move, -getMaxAngularAcceleration(), getMaxAngularAcceleration()));
	}

}