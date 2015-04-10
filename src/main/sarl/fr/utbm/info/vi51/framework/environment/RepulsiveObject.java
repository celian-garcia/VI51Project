package fr.utbm.info.vi51.framework.environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;

public class RepulsiveObject extends AbstractSituatedObject {

	private static final long serialVersionUID = 1286955090621696919L;

	private final UUID id;
	private float kinematicStrength;
	private float steeringStrength;
	
	private transient MotionInfluence motionInfluence = null;
	private transient List<Influence> otherInfluences = new ArrayList<>();

	/**
	 * @param shape the shape of the body, considering that it is centered at the (0,0) position.
	 * @param position is the position of the object.
	 */
	public RepulsiveObject(UUID id, Shape2f<?> shape, Point2f position, float k_strength, float s_strength) {
		super(shape, position);
		this.id = id;
		this.kinematicStrength = k_strength;
		this.kinematicStrength = s_strength;
	}
	
	@Override
	public RepulsiveObject clone() {
		RepulsiveObject clone = (RepulsiveObject) super.clone();
		clone.motionInfluence = null;
		clone.otherInfluences = new ArrayList<>();
		return clone;
	}
	
	/** Replies the id of this object.
	 * 
	 * @return the id of this object.
	 */
	public UUID getID() {
		return this.id;
	}
//	
//	
//	/** Invoked to send the given influence to the environment.
//	 *
//	 * @param influence the influence.
//	 */
//	public void influence(Influence influence) {
//		if (influence != null) {
//			if (influence instanceof MotionInfluence) {
//				MotionInfluence mi = (MotionInfluence) influence;
//				if (mi.getInfluencedObject() == null || mi.getInfluencedObject().equals(getID())) {
//					switch(mi.getType()) {
//					case KINEMATIC:
//						influenceKinematic(mi.getLinearInfluence(), mi.getAngularInfluence());
//						break;
//					case STEERING:
//						influenceSteering(mi.getLinearInfluence(), mi.getAngularInfluence());
//						break;
//					}
//				} else {
//					this.otherInfluences.add(mi);
//				}
//			} else {
//				this.otherInfluences.add(influence);
//			}
//		}
//	}
//
//	/** Invoked to send the influence to the environment.
//	 * 
//	 * @param linearInfluence is the linear influence to apply on the object.
//	 * @param angularInfluence is the angular influence to apply on the object.
//	 */
//	public void influenceKinematic(Vector2f linearInfluence, float angularInfluence) {
//		Vector2f li;
//		if (linearInfluence!=null) {
//			li = new Vector2f(linearInfluence);
//			float nSpeed = li.length();
//			if (nSpeed>getKinematicStrength()) {
//				li.normalize();
//				li.scale(getKinematicStrength());
//			}
//		}
//		else {
//			li = new Vector2f();
//		}
//		float ai = MathUtil.clamp(angularInfluence, -getMaxAngularSpeed(), getMaxAngularSpeed());
//		this.motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, getID(), li, ai);
//	}
//	
//	/** Invoked to send the influence to the environment.
//	 * 
//	 * @param linearInfluence is the linear influence to apply on the object.
//	 * @param angularInfluence is the angular influence to apply on the object.
//	 */
//	public void influenceSteering(Vector2f linearInfluence, float angularInfluence) {
//		Vector2f li;
//		if (linearInfluence!=null) {
//			li = new Vector2f(linearInfluence);
//			float nSpeed = li.length();
//			if (nSpeed>getStrength()) {
//				li.normalize();
//				li.scale(getStrength());
//			}
//		}
//		else {
//			li = new Vector2f();
//		}
//		float ai = MathUtil.clamp(angularInfluence, -getMaxAngularAcceleration(), getMaxAngularAcceleration());
//		this.motionInfluence = new MotionInfluence(DynamicType.STEERING, getID(), li, ai);
//	}
//
//	/** Invoked to send the influence to the environment.
//	 * 
//	 * @param linearInfluence is the linear influence to apply on the object.
//	 */
//	public void influenceKinematic(Vector2f linearInfluence) {
//		influenceKinematic(linearInfluence, 0f);
//	}
//	
//	/** Invoked to send the influence to the environment.
//	 * 
//	 * @param linearInfluence is the linear influence to apply on the object.
//	 */
//	public void influenceSteering(Vector2f linearInfluence) {
//		influenceSteering(linearInfluence, 0f);
//	}
//	
//	/** Invoked to send the influence to the environment.
//	 * 
//	 * @param angularInfluence is the angular influence to apply on the object.
//	 */
//	public void influenceKinematic(float angularInfluence) {
//		influenceKinematic(null, angularInfluence);
//	}
//	
//	/** Invoked to send the influence to the environment.
//	 * 
//	 * @param angularInfluence is the angular influence to apply on the object.
//	 */
//	public void influenceSteering(float angularInfluence) {
//		influenceSteering(null, angularInfluence);
//	}

	public float getKinematicStrength() {
		return this.kinematicStrength;
	}
	
	public float getSteeringStrength() {
		return this.steeringStrength;
	}

}
