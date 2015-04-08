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

import java.util.UUID;

import fr.utbm.info.vi51.framework.time.TimeManager;

/**
 * Situated environment.  
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public interface Environment {

	/** Replies the time manager of this environment.
	 * 
	 * @return the time manager of this environment.
	 */
	public TimeManager getTimeManager();
	
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
	
	/** Replies number of bodies in the environment.
	 * 
	 * @return the number of bodies in the environment.
	 */
	public int getAgentBodyNumber();

	/** Replies the agent body associated to the given agent.
	 * 
	 * @param agentId
	 * @return the agent body or <code>null</code>.
	 */
	public AgentBody getAgentBodyFor(UUID agentId);
	
	/** Replies the objects in the environment.
	 *
	 * The replied collection is unmodifiable.
	 *
	 * @return the objects in the environment.
	 */
	public Iterable<? extends SituatedObject> getAllObjects();

	/** Replies the bodies in the environment.
	 *
	 * The replied collection is unmodifiable.
	 *
	 * @return the bodies in the environment.
	 */
	public Iterable<? extends AgentBody> getAgentBodies();

	/** Run the environment behaviour: apply influences, compute perceptions.
	 */
	public void runBehaviour();
	
	/** Add listener on environment events.
	 * 
	 * @param listener
	 */
	public void addEnvironmentListener(EnvironmentListener listener);

	/** Remove listener on environment events.
	 * 
	 * @param listener
	 */
	public void removeEnvironmentListener(EnvironmentListener listener);
	
}