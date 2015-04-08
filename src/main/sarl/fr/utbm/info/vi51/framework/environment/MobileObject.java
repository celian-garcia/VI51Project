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

import fr.utbm.info.vi51.framework.math.Vector2f;

/**
 * Object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public interface MobileObject extends SituatedObject {

	/** Replies the orientation of the object.
	 * 
	 * @return the angle of orientation from (1,0).
	 */
	float getAngle();
	
	/** Replies the orientation of the object.
	 * 
	 * @return the orientation direction.
	 */
	Vector2f getDirection();
	
	/** Replies the max linear speed.
	 * 
	 * @return the max linear speed.
	 */
	float getMaxLinearSpeed();

	/** Replies the max angular speed.
	 * 
	 * @return the max angular speed.
	 */
	float getMaxAngularSpeed();

	/** Replies the max linear acceleration.
	 * 
	 * @return the max linear acceleration.
	 */
	float getMaxLinearAcceleration();

	/** Replies the max angular acceleration.
	 * 
	 * @return the max angular acceleration.
	 */
	float getMaxAngularAcceleration();
	
	/** Replies the current angular speed.
	 * 
	 * @return the current angular speed.
	 */
	float getCurrentAngularSpeed();

	/** Replies the current linear speed.
	 * 
	 * @return the current linear speed.
	 */
	float getCurrentLinearSpeed();

	/** Replies the current linear motion.
	 * 
	 * @return the current linear motion.
	 */
	Vector2f getCurrentLinearMotion();

}