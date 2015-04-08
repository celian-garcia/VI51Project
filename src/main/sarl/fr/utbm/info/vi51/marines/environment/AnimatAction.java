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
package fr.utbm.info.vi51.marines.environment;

import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.math.Vector2f;

/**
 * Real action to apply in the world.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
class AnimatAction {

	private final AgentBody body;
	private final Vector2f move;
	private final float rotation;
	
	/**
	 * @param object is the animat body.
	 * @param move is the translation.
	 * @param rotation is the rotation.
	 */
	public AnimatAction(AgentBody object, Vector2f move, float rotation) {
		this.body = object;
		this.move = move;
		this.rotation = rotation;
	}
	
	/** Replies the moved object.
	 * 
	 * @return the moved object.
	 */
	public AgentBody getObjectToMove() {
		return this.body;
	}
	
	/** Replies the translation.
	 * 
	 * @return the translation.
	 */
	public Vector2f getTranslation() {
		return this.move;
	}
	
	/** Replies the rotation.
	 * 
	 * @return the rotation.
	 */
	public float getRotation() {
		return this.rotation;
	}

}