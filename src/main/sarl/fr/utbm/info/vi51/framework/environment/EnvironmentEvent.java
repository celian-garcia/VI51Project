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

import java.util.EventObject;

import fr.utbm.info.vi51.framework.gui.WorldModelStateProvider;

/**
 * Event in environment.  
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class EnvironmentEvent extends EventObject {

	private static final long serialVersionUID = 7299337937504180602L;

	/**
	 * @param source
	 */
	public EnvironmentEvent(Environment source) {
		super(source);
	}
	
	/** Replies the environment.
	 * 
	 * @return the environment.
	 */
	public Environment getEnvironment() {
		return (Environment)getSource();
	}
	
	/** Replies the state provider.
	 * 
	 * @return the state provider.
	 */
	public WorldModelStateProvider getStateProvider() {
		return (WorldModelStateProvider)getSource();
	}

}