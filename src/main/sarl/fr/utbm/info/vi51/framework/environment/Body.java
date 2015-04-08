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

import java.util.Collection;
import java.util.UUID;

import fr.utbm.info.vi51.framework.math.Vector2f;

/**
 * Provides the services of a body.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public interface Body {

	/** Replies the id of this body.
	 * 
	 * @return the id of this body.
	 */
	UUID getID();

	/** Invoked to send the influence to the environment.
	 * 
	 * @param linearInfluence is the linear influence to apply on the object.
	 * @param angularInfluence is the angular influence to apply on the object.
	 */
	void influenceKinematic(Vector2f linearInfluence, float angularInfluence);
	
	/** Invoked to send the influence to the environment.
	 * 
	 * @param linearInfluence is the linear influence to apply on the object.
	 * @param angularInfluence is the angular influence to apply on the object.
	 */
	void influenceSteering(Vector2f linearInfluence, float angularInfluence);

	/** Invoked to send the influence to the environment.
	 * 
	 * @param linearInfluence is the linear influence to apply on the object.
	 */
	void influenceKinematic(Vector2f linearInfluence);
	
	/** Invoked to send the influence to the environment.
	 * 
	 * @param linearInfluence is the linear influence to apply on the object.
	 */
	void influenceSteering(Vector2f linearInfluence);
	
	/** Invoked to send the influence to the environment.
	 * 
	 * @param angularInfluence is the angular influence to apply on the object.
	 */
	void influenceKinematic(float angularInfluence);
	
	/** Invoked to send the influence to the environment.
	 * 
	 * @param angularInfluence is the angular influence to apply on the object.
	 */
	void influenceSteering(float angularInfluence);
	
	/** Replies all the perceived objects.
	 * 
	 * @return the perceived objects.
	 */
	Collection<Percept> getPerceivedObjects();

}