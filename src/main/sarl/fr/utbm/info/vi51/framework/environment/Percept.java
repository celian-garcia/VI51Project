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

import java.io.Serializable;
import java.util.UUID;

import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;

/**
 * Defined a perception unit.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class Percept implements MobileObject, Serializable {

	private static final long serialVersionUID = -6553882246151660857L;

	private final UUID bodyId;
	private Shape2f<?> shape;
	private Point2f position;
	private final float angle;
	private final Serializable type;
	private final float maxLinearSpeed;
	private final float maxLinearAcceleration;
	private Vector2f currentLinearMotion;
	private final float maxAngularSpeed;
	private final float maxAngularAcceleration;
	private final float currentAngularSpeed;
	private final String name;
	
	/**
	 * @param perceivedObject is the perceived object.
	 */
	public Percept(SituatedObject perceivedObject) {
		this(perceivedObject, null);
	}
	
	/**
	 * @param perceivedObject is the perceived object.
	 * @param type the type of the object.
	 */
	public Percept(SituatedObject perceivedObject, Serializable type) {
		this.name = perceivedObject.getName();
		this.shape = perceivedObject.getShape();
		this.position = perceivedObject.getPosition().clone();
		if (type == null) {
			type = perceivedObject.getType();
			if (type == null) {
				type = perceivedObject.getClass().getName();
			}
		}
		this.type = type;
		if (perceivedObject instanceof MobileObject) {
			MobileObject mo = (MobileObject) perceivedObject;
			this.angle = mo.getAngle();
			this.maxAngularAcceleration = mo.getMaxAngularAcceleration();
			this.maxAngularSpeed = mo.getMaxAngularSpeed();
			this.maxLinearAcceleration = mo.getMaxLinearAcceleration();
			this.maxLinearSpeed = mo.getMaxLinearSpeed();
			this.currentAngularSpeed = mo.getCurrentAngularSpeed();
			this.currentLinearMotion = mo.getCurrentLinearMotion().clone();
		} else {
			this.angle = 0f;
			this.maxAngularAcceleration = 0f;
			this.maxAngularSpeed = 0f;
			this.maxLinearAcceleration = 0f;
			this.maxLinearSpeed = 0f;
			this.currentAngularSpeed = 0f;
			this.currentLinearMotion = null;
		}
		if (perceivedObject instanceof AgentBody) {
			this.bodyId = ((AgentBody) perceivedObject).getID();
		} else {
			this.bodyId = null;
		}
	}
	
	@Override
	public Percept clone() {
		try {
			Percept clone = (Percept) super.clone();
			clone.currentLinearMotion = this.currentLinearMotion.clone();
			clone.position = this.position.clone();
			clone.shape = this.shape.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}

	/** Replies the id of the body.
	 *
	 * @return the id of the body.
	 */
	public UUID getID() {
		return this.bodyId;
	}

	@Override
	public Shape2f<?> getShape() {
		return this.shape;
	}

	@Override
	public float getX() {
		return this.position.getX();
	}

	@Override
	public float getY() {
		return this.position.getY();
	}

	@Override
	public Point2f getPosition() {
		return this.position;
	}

	@Override
	public float getAngle() {
		return this.angle;
	}

	@Override
	public Vector2f getDirection() {
		return Vector2f.toOrientationVector(this.angle);
	}

	@Override
	public float getMaxLinearSpeed() {
		return this.maxLinearSpeed;
	}

	@Override
	public float getMaxAngularSpeed() {
		return this.maxAngularSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return this.maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularAcceleration() {
		return this.maxAngularAcceleration;
	}

	@Override
	public float getCurrentAngularSpeed() {
		return this.currentAngularSpeed;
	}

	@Override
	public float getCurrentLinearSpeed() {
		if (this.currentLinearMotion == null) {
			return 0;
		}
		return this.currentLinearMotion.length();
	}

	@Override
	public Vector2f getCurrentLinearMotion() {
		if (this.currentLinearMotion == null) {
			return new Vector2f();
		}
		return this.currentLinearMotion;
	}

	@Override
	public Serializable getType() {
		return this.type;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		if (this.name != null) {
			return this.name;
		}
		return super.toString();
	}
		
}