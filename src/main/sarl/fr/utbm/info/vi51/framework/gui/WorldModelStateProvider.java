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
package fr.utbm.info.vi51.framework.gui;

import fr.utbm.info.vi51.framework.environment.WorldModelState;
import fr.utbm.info.vi51.framework.math.Point2f;


/**
 * State provider for the world model.  
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public interface WorldModelStateProvider {

	/** Set the mouse target.
	 * 
	 * @param target
	 */
	public void setMouseTarget(Point2f target);

	/** Replies the width of the environment.
	 * 
	 * @return the width of the environment.
	 */
	public float getWidth();
	
	/** Replies the height of the environment.
	 * 
	 * @return the height of the environment.
	 */
	public float getHeight();
	
	/** Replies the state of the world model.
	 * 
	 * @return the state of the world model.
	 */
	public WorldModelState getState();

}