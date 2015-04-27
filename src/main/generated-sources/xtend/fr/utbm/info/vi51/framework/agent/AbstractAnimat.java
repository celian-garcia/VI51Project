package fr.utbm.info.vi51.framework.agent;

import com.google.common.base.Objects;
import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.agent.PhysicEnvironment;
import fr.utbm.info.vi51.framework.agent.StandardPhysicEnvironment;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.StopSimulation;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.lang.annotation.EarlyExit;
import io.sarl.lang.annotation.FiredEvent;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.Percept;
import java.util.UUID;

@SuppressWarnings("all")
public class AbstractAnimat extends Agent {
  protected DynamicType behaviorType;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    Object _get_1 = occurrence.parameters[1];
    StandardPhysicEnvironment physicSkill = new StandardPhysicEnvironment(
      ((UUID) _get), 
      ((UUID) _get_1));
    Object _get_2 = occurrence.parameters[2];
    this.behaviorType = ((DynamicType) _get_2);
    this.<StandardPhysicEnvironment>setSkill(PhysicEnvironment.class, physicSkill);
  }
  
  public float getMaxLinear(final fr.utbm.info.vi51.framework.environment.Percept p) {
    float _xifexpression = (float) 0;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      _xifexpression = p.getMaxLinearAcceleration();
    } else {
      _xifexpression = p.getMaxLinearSpeed();
    }
    return _xifexpression;
  }
  
  public float getMaxAngular(final fr.utbm.info.vi51.framework.environment.Percept p) {
    float _xifexpression = (float) 0;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      _xifexpression = p.getMaxAngularAcceleration();
    } else {
      _xifexpression = p.getMaxAngularSpeed();
    }
    return _xifexpression;
  }
  
  public void emitInfluence(final BehaviourOutput output) {
    if ((output != null)) {
      DynamicType _type = output.getType();
      boolean _tripleEquals = (_type == DynamicType.STEERING);
      if (_tripleEquals) {
        Vector2f _linear = output.getLinear();
        float _angular = output.getAngular();
        this.influenceSteering(_linear, _angular);
      } else {
        Vector2f _linear_1 = output.getLinear();
        float _angular_1 = output.getAngular();
        this.influenceKinematic(_linear_1, _angular_1);
      }
    }
  }
  
  @Percept
  public void _handle_StopSimulation_1(final StopSimulation occurrence) {
    this.killMe();
  }
  
  public Vector2f repulsiveVector(final Point2f obj, final Point2f me, final float s) {
    Vector2f v = new Vector2f();
    v.sub(me, obj);
    float dist = v.length();
    v.normalize();
    v.scale((((s * s) * s) / ((dist * dist) * dist)));
    return v;
  }
  
  public float computeDistanceMin(final Point2f obj, final Rectangle2f objb, final Point2f me, final Rectangle2f meb) {
    Point2f _upper = objb.getUpper();
    float _x = _upper.getX();
    Point2f _lower = objb.getLower();
    float _x_1 = _lower.getX();
    float _minus = (_x - _x_1);
    float objr = (_minus / 2);
    Point2f _upper_1 = meb.getUpper();
    float _x_2 = _upper_1.getX();
    Point2f _lower_1 = meb.getLower();
    float _x_3 = _lower_1.getX();
    float _minus_1 = (_x_2 - _x_3);
    float mer = (_minus_1 / 2);
    return (objr + mer);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic()}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic()
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic() {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic();
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(float)}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(float)
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final float angularInfluence) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(angularInfluence);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f)}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f)
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final Vector2f linearInfluence) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(linearInfluence);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f,float)}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f,float)
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final Vector2f linearInfluence, final float angularInfluence) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(linearInfluence, angularInfluence);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering()}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering()
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering() {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering();
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(float)}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(float)
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final float angularInfluence) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(angularInfluence);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f)}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f)
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final Vector2f linearInfluence) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(linearInfluence);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f,float)}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f,float)
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final Vector2f linearInfluence, final float angularInfluence) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(linearInfluence, angularInfluence);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Lifecycle#killMe()}.
   * 
   * @see io.sarl.core.Lifecycle#killMe()
   */
  @EarlyExit
  @FiredEvent({ AgentKilled.class, Destroy.class })
  @Generated
  @ImportedCapacityFeature(Lifecycle.class)
  protected void killMe() {
    getSkill(io.sarl.core.Lifecycle.class).killMe();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Lifecycle#spawnInContext(java.lang.Class<? extends io.sarl.lang.core.Agent>,io.sarl.lang.core.AgentContext,java.lang.Object[])}.
   * 
   * @see io.sarl.core.Lifecycle#spawnInContext(java.lang.Class<? extends io.sarl.lang.core.Agent>,io.sarl.lang.core.AgentContext,java.lang.Object[])
   */
  @FiredEvent(AgentSpawned.class)
  @Generated
  @ImportedCapacityFeature(Lifecycle.class)
  protected UUID spawnInContext(final Class<? extends Agent> agentClass, final AgentContext context, final Object... params) {
    return getSkill(io.sarl.core.Lifecycle.class).spawnInContext(agentClass, context, params);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Lifecycle#spawnInContextWithID(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.util.UUID,io.sarl.lang.core.AgentContext,java.lang.Object[])}.
   * 
   * @see io.sarl.core.Lifecycle#spawnInContextWithID(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.util.UUID,io.sarl.lang.core.AgentContext,java.lang.Object[])
   */
  @FiredEvent(AgentSpawned.class)
  @Generated
  @ImportedCapacityFeature(Lifecycle.class)
  protected UUID spawnInContextWithID(final Class<? extends Agent> agentClass, final UUID agentID, final AgentContext context, final Object... params) {
    return getSkill(io.sarl.core.Lifecycle.class).spawnInContextWithID(agentClass, agentID, context, params);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public AbstractAnimat(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public AbstractAnimat(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
