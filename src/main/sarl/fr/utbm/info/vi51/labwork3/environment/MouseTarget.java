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
package fr.utbm.info.vi51.labwork3.environment;

import fr.utbm.info.vi51.framework.environment.AbstractSituatedObject;
import fr.utbm.info.vi51.framework.math.Circle2f;

/**
 * Situated object representing the mouse position.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public final class MouseTarget extends AbstractSituatedObject {

	private static final long serialVersionUID = 3960871126454165695L;

	/**
	 * @param x
	 * @param y
	 */
	public MouseTarget(float x, float y) {
		super(new Circle2f(0, 0, 1f), x, y);
		setType("TARGET");
	}
	
	/** Set the position of the mouse target.
	 * 
	 * @param x
	 * @param y
	 */
	public void setMousePosition(float x, float y) {
		// *** CAUTION ***
		// Changing directly the position of the target without going through the influence
		// solver may cause collision between the target and the agents.
		setPosition(x, y);
	}
	
}