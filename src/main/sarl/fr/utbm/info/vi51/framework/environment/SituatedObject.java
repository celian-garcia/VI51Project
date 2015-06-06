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

import fr.utbm.info.vi51.framework.math.Point2f;

/**
 * Object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public interface SituatedObject extends ShapedObject, Cloneable {

	/** Clone the object.
	 *
	 * @return the clone.
	 */
	SituatedObject clone();
	
	/** Replies the type of the object.
	 * 
	 * @return the type of the object.
	 */
	Serializable getType();
	
	/** Replies the name of the object.
	 *
	 * The name is defined only for displaying purpose.
	 * 
	 * @return the name of the object.
	 */
	String getName();

	/** Replies the position of the object.
	 * 
	 * @return the x-coordinate of the position of this object.
	 */
	float getX();
	
	/** Replies the position of the object.
	 * 
	 * @return the y-coordinate of the position of this object.
	 */
	float getY();

	/** Replies the position of the object.
	 * 
	 * @return the position of the object.
	 */
	Point2f getPosition();
	
}