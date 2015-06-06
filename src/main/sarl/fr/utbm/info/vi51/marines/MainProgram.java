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
package fr.utbm.info.vi51.marines;

import io.sarl.lang.core.Agent;
import fr.utbm.info.vi51.framework.FrameworkLauncher;
import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.gui.BehaviorTypeSelector;
import fr.utbm.info.vi51.framework.gui.FrameworkGUI;
import fr.utbm.info.vi51.framework.util.LocalizedString;
import fr.utbm.info.vi51.framework.util.SpawnMapping;
import fr.utbm.info.vi51.marines.agent.Follower;
import fr.utbm.info.vi51.marines.agent.Leader;
import fr.utbm.info.vi51.marines.agent.Master;
import fr.utbm.info.vi51.marines.environment.WorldModel;
import fr.utbm.info.vi51.marines.formation.BodyGuardFormation;
import fr.utbm.info.vi51.marines.formation.Formation;
import fr.utbm.info.vi51.marines.formation.LineFormation;
//import fr.utbm.info.vi51.marines.formation.RectangleFormation;
//import fr.utbm.info.vi51.marines.formation.VFormation;
import fr.utbm.info.vi51.marines.gui.GUI;

/**
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class MainProgram {

	private static float WORLD_SIZE_X = 1400;
	private static float WORLD_SIZE_Y = 700;
	public static int SLOT_COUNT = 10;
	public static int MASTER_SLOT_COUNT = 5;
	
	/** Main program.
	 * 
	 * @param argv are the command line arguments.
	 * @throws Exception 
	 */	
	public static void main(String[] argv) throws Exception {
		
		System.out.println(LocalizedString.getString(MainProgram.class, "INTRO_MESSAGE")); //$NON-NLS-1$
		
		DynamicType type = BehaviorTypeSelector.open();
		if (type == null) {
			System.exit(0);
		}
		
		// Initialize formations
		// First formation is the master formation 
		// Others formation are the leader formations
		Formation[] formations = new Formation[]{
				new LineFormation(MASTER_SLOT_COUNT),
				new BodyGuardFormation (SLOT_COUNT),
				new BodyGuardFormation (SLOT_COUNT),
				new BodyGuardFormation (SLOT_COUNT),
				new BodyGuardFormation (SLOT_COUNT),
				new BodyGuardFormation (SLOT_COUNT)
		};
		
		
		WorldModel environment = new WorldModel(WORLD_SIZE_X, WORLD_SIZE_Y);
		
		environment.createMaster();
		environment.createLeader();
		environment.createLeader();
		environment.createLeader();
		environment.createLeader();

		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createFollower();
		environment.createRock(200f, 200f);
		environment.createRock(300f, 300f);
		environment.createRock(400f, 400f);
		environment.createRock(400f, 500f);
		environment.createRock(500f, 400f);
		environment.createRock(600f, 300f);
		
		FrameworkGUI gui = new GUI(WORLD_SIZE_X, WORLD_SIZE_Y, environment.getTimeManager(), formations);

		
		// Initialize parameters to send to janus
		Object [] params = new Object[formations.length];
		int i = 0;
		for (Formation f:formations) {
			params[i++] = f;
		}
		
		
		FrameworkLauncher.launchSimulation(
				environment,
				new ApplicationMapping(),
				type,
				gui,
				params);
	}
	
	private static class ApplicationMapping extends SpawnMapping {

		@Override
		public Class<? extends Agent> getAgentTypeForBody(AgentBody body) {

			Object type = body.getType();
			if ("LEADER".equals(type)) {
				return Leader.class;
			}
			if ("FOLLOWER".equals(type)) {
				return Follower.class;
			}
			if ("MASTER".equals(type)) {
				return Master.class;
			}
			throw new IllegalArgumentException();
		}
		
	}

}