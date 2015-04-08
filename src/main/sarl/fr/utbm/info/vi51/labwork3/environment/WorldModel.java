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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import fr.utbm.info.vi51.framework.environment.AbstractEnvironment;
import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.Influence;
import fr.utbm.info.vi51.framework.environment.MotionInfluence;
import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.environment.SituatedObject;
import fr.utbm.info.vi51.framework.gui.WorldModelStateProvider;
import fr.utbm.info.vi51.framework.math.Circle2f;
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.time.StepTimeManager;
import fr.utbm.info.vi51.framework.time.TimeManager;
import fr.utbm.info.vi51.framework.util.CollectionUtil;
import fr.utbm.info.vi51.framework.util.LocalizedString;

/**
 * Model of the world.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class WorldModel extends AbstractEnvironment implements WorldModelStateProvider {

	private final static float RABBIT_SIZE = 20f;
	
	private MouseTarget mouseTarget = null;
	
	/**
	 * @param width is the width of the world.
	 * @param height is the height of the world.
	 */
	public WorldModel(float width, float height) {
		super(width, height, new StepTimeManager(500));
	}
	
	/** {@inheritDoc}
	 */
	public void setMouseTarget(Point2f target) {
		if (target==null) this.mouseTarget = null;
		else this.mouseTarget = new MouseTarget(target.getX(), target.getY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Percept> computePerceptionsFor(AgentBody agent) {
		List<Percept> allPercepts = new ArrayList<Percept>();
		if (agent!=null) {
			float x1 = agent.getX();
			float y1 = agent.getY();
			
			// add mouse target in perceptions
			if (this.mouseTarget!=null) {
				allPercepts.add(new Percept(this.mouseTarget));
			}

			float bestDistance = Float.MAX_VALUE;
			AgentBody nearestBody = null;
			
			for(AgentBody b1 : getAgentBodies()) {
				if (b1!=agent) {
					float x2 = b1.getX();
					float y2 = b1.getY();
					float distance = new Vector2f(x2-x1,y2-y1).length();
					if (distance<bestDistance) {
						bestDistance = distance;
						nearestBody = b1;
					}
				}
			}
			
			if (nearestBody!=null) {
				allPercepts.add(new Percept(nearestBody));
			}
		}
		
		return allPercepts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyInfluences(Collection<MotionInfluence> motionInfluences,
			Collection<Influence> otherInfluences, TimeManager timeManager) {
		List<MotionInfluence> influenceList = new ArrayList<MotionInfluence>(motionInfluences);
		List<AnimatAction> actions = new ArrayList<AnimatAction>(influenceList.size());
		
		// Compute actions
		for(int index1=0; index1<influenceList.size(); index1++) {
			MotionInfluence inf1 = influenceList.get(index1);
			AgentBody body1 = getAgentBodyFor(inf1.getEmitter());
			if (body1!=null) {
				Vector2f move;
				float rotation;
				if (inf1.getType()==DynamicType.STEERING) {
					move = computeSteeringTranslation(body1, inf1.getLinearInfluence(), timeManager);
					rotation = computeSteeringRotation(body1, inf1.getAngularInfluence(), timeManager);
				}
				else {
					move = computeKinematicTranslation(body1, inf1.getLinearInfluence(), timeManager);
					rotation = computeKinematicRotation(body1, inf1.getAngularInfluence(), timeManager);
				}
				
				Shape2f<?> body1Bounds = body1.getShape();
				
				// Trivial collision detection
				for(int index2=index1+1; index2<influenceList.size(); index2++) {
					MotionInfluence inf2 = influenceList.get(index2);
					AgentBody body2 = getAgentBodyFor(inf2.getEmitter());
					if (body2!=null) {
						Shape2f<?> body2Bounds = body2.getShape();
						if (body1Bounds.intersects(body2Bounds)) {
							move.set(0,0);
							break;
						}
					}
				}
				
				actions.add(new AnimatAction(body1, move, rotation));
				
			}
		}
		
		// Apply the actions
		for(AnimatAction action : actions) {
			AgentBody body = action.getObjectToMove();
			if (body!=null) {
				move(
						body,
						action.getTranslation(),
						action.getRotation());
			}
		}
	}

	@Override
	public Iterable<? extends SituatedObject> getAllObjects() {
		if (this.mouseTarget != null) {
			return CollectionUtil.newIterable(getAgentBodies(), this.mouseTarget);
		}
		return getAgentBodies();
	}

	@Override
	protected List<Influence> computeEndogenousBehaviorInfluences() {
		return Collections.emptyList();
	}
	
	private void createBody(String type) {
		AgentBody body = new AgentBody(
				UUID.randomUUID(),
				new Circle2f(0f, 0f, RABBIT_SIZE), // body
				5f,						// max linear speed m/s
				.5f,						// max linear acceleration (m/s)/s
				MathUtil.PI/4f,				// max angular speed r/s
				MathUtil.PI/10f,			// max angular acceleration (r/s)/s
				null); // no frustum since computePerceptionsFor() is not using this parameter
		body.setName(LocalizedString.getString(WorldModel.class, type, getAgentBodyNumber() + 1));
		body.setType(type);
		addAgentBody(
				body,
				randomPosition(),
				(float) Math.random() * MathUtil.TWO_PI);
	}

	/** Create the body of a leader.
	 */
	public void createLeader() {
		createBody("LEADER");
	}
	
	/** Create the body of a leader.
	 */
	public void createFollower() {
		createBody("FOLLOWER");
	}

	protected Point2f randomPosition() {
		float x = (float) Math.random() * getWidth() - RABBIT_SIZE;
		float y = (float) Math.random() * getHeight() - RABBIT_SIZE;
		return new Point2f(x, y);
	}

	@Override
	protected void onAgentBodyCreated(AgentBody body) {
		//
	}

	@Override
	protected void onAgentBodyDestroyed(AgentBody body) {
		//
	}
	
}