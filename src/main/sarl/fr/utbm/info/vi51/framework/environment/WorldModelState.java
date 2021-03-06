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

import java.util.ArrayList;
import java.util.List;

/**
 * State of the world model.  
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class WorldModelState {

	private final List<SituatedObject> objects;
	
	/**
	 * @param environment
	 */
	public WorldModelState(Environment environment) {
		this.objects = new ArrayList<>();
		for (SituatedObject o : environment.getAllObjects()) {
			this.objects.add(o.clone());
		}
	}
	
	/** Replies the objects in the environment.
	 * 
	 * @return the objects in the environment.
	 */
	public Iterable<SituatedObject> getObjects() {
		return this.objects;
	}

}