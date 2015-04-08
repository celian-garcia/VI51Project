package fr.utbm.info.vi51.labwork3.agent;

import com.google.common.base.Objects;

import fr.utbm.info.vi51.framework.agent.AbstractAnimat;
import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.PerceptionEvent;
import fr.utbm.info.vi51.framework.environment.SimulationAgentReady;
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.labwork3.behavior.AlignBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.SeekBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.WanderBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.kinematic.KinematicAlignBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.kinematic.KinematicSeekBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.kinematic.KinematicWanderBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.steering.SteeringAlignBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.steering.SteeringFaceBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.steering.SteeringSeekBehaviour;
import fr.utbm.info.vi51.labwork3.behavior.steering.SteeringWanderBehaviour;
import fr.utbm.info.vi51.labwork3.formation.Formation;
import fr.utbm.info.vi51.labwork3.formation.FormationAssignment;
import fr.utbm.info.vi51.labwork3.formation.FormationSlot;
import io.sarl.core.AgentSpawned;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.FiredEvent;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Percept;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Space;
import io.sarl.lang.core.SpaceID;

import java.util.UUID;

@SuppressWarnings("all")
public class Follower extends AbstractAnimat {
  protected SeekBehaviour seekBehaviour;
  
  protected AlignBehaviour alignBehaviour;
  
  protected WanderBehaviour wanderBehaviour;
  
  protected final float STOP_DISTANCE = 3f;
  
  protected final float STOP_RADIUS = (MathUtil.PI / 10f);
  
  protected final float SLOW_RADIUS = (MathUtil.PI / 4f);
  
  protected final float WANDER_CIRCLE_DISTANCE = 20f;
  
  protected final float WANDER_CIRCLE_RADIUS = 10f;
  
  protected final float WANDER_MAX_ROTATION = (MathUtil.PI / 4f);
  
  protected FormationSlot formationSlot;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
    Object _get = occurrence.parameters[3];
    Formation formation = ((Formation) _get);
    Object _get_1 = occurrence.parameters[4];
    FormationAssignment assignment = ((FormationAssignment) _get_1);
    int index = assignment.allocate(false);
    FormationSlot _slotAt = formation.getSlotAt(index);
    this.formationSlot = _slotAt;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      SteeringSeekBehaviour _steeringSeekBehaviour = new SteeringSeekBehaviour();
      this.seekBehaviour = _steeringSeekBehaviour;
      SteeringAlignBehaviour alignB = new SteeringAlignBehaviour(this.STOP_RADIUS, this.SLOW_RADIUS);
      SteeringFaceBehaviour faceB = new SteeringFaceBehaviour(this.STOP_DISTANCE, alignB);
      this.alignBehaviour = alignB;
      SteeringWanderBehaviour _steeringWanderBehaviour = new SteeringWanderBehaviour(
        this.WANDER_CIRCLE_DISTANCE, 
        this.WANDER_CIRCLE_RADIUS, 
        this.WANDER_MAX_ROTATION, faceB);
      this.wanderBehaviour = _steeringWanderBehaviour;
    } else {
      KinematicSeekBehaviour _kinematicSeekBehaviour = new KinematicSeekBehaviour();
      this.seekBehaviour = _kinematicSeekBehaviour;
      KinematicAlignBehaviour _kinematicAlignBehaviour = new KinematicAlignBehaviour(this.STOP_RADIUS);
      this.alignBehaviour = _kinematicAlignBehaviour;
      KinematicWanderBehaviour _kinematicWanderBehaviour = new KinematicWanderBehaviour();
      this.wanderBehaviour = _kinematicWanderBehaviour;
    }
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    this.emit(_simulationAgentReady);
  }
  
  @Percept
  public void _handle_PerceptionEvent_1(final PerceptionEvent occurrence) {
    if ((this.formationSlot == null)) {
      Point2f _position = occurrence.body.getPosition();
      Vector2f _direction = occurrence.body.getDirection();
      float _currentLinearSpeed = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear = this.getMaxLinear(occurrence.body);
      float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position, _direction, _currentLinearSpeed, _maxLinear, _currentAngularSpeed, _maxAngular);
      this.emitInfluence(_runWander);
    } else {
      Point2f position = this.formationSlot.getGlobalPosition();
      Point2f _position_1 = occurrence.body.getPosition();
      float _currentLinearSpeed_1 = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear_1 = this.getMaxLinear(occurrence.body);
      BehaviourOutput bo1 = this.seekBehaviour.runSeek(_position_1, _currentLinearSpeed_1, _maxLinear_1, position);
      Vector2f orientation = this.formationSlot.getGlobalOrientation();
      Vector2f _direction_1 = occurrence.body.getDirection();
      float _currentAngularSpeed_1 = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular_1 = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runAlign = this.alignBehaviour.runAlign(_direction_1, _currentAngularSpeed_1, _maxAngular_1, orientation);
      bo1.setAngular(_runAlign);
      this.emitInfluence(bo1);
    }
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected void emit(final Event e) {
    getSkill(io.sarl.core.DefaultContextInteractions.class).emit(e);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event,io.sarl.lang.core.Scope<io.sarl.lang.core.Address>)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event,io.sarl.lang.core.Scope<io.sarl.lang.core.Address>)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected void emit(final Event e, final Scope<Address> scope) {
    getSkill(io.sarl.core.DefaultContextInteractions.class).emit(e, scope);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#getDefaultAddress()}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#getDefaultAddress()
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected Address getDefaultAddress() {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).getDefaultAddress();
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#getDefaultContext()}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#getDefaultContext()
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected AgentContext getDefaultContext() {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).getDefaultContext();
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#getDefaultSpace()}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#getDefaultSpace()
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected EventSpace getDefaultSpace() {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).getDefaultSpace();
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultContext(io.sarl.lang.core.AgentContext)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultContext(io.sarl.lang.core.AgentContext)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultContext(final AgentContext context) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultContext(context);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultContext(java.util.UUID)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultContext(java.util.UUID)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultContext(final UUID contextID) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultContext(contextID);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.Space)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.Space)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultSpace(final Space space) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultSpace(space);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.SpaceID)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.SpaceID)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultSpace(final SpaceID space) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultSpace(space);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultSpace(java.util.UUID)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultSpace(java.util.UUID)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultSpace(final UUID space) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultSpace(space);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isInDefaultSpace(io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isInDefaultSpace(io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isInDefaultSpace(final Event event) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isInDefaultSpace(event);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#receive(java.util.UUID,io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#receive(java.util.UUID,io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected void receive(final UUID receiver, final Event e) {
    getSkill(io.sarl.core.DefaultContextInteractions.class).receive(receiver, e);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#spawn(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.lang.Object[])}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#spawn(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.lang.Object[])
   */
  @FiredEvent(AgentSpawned.class)
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected UUID spawn(final Class<? extends Agent> aAgent, final Object... params) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).spawn(aAgent, params);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public Follower(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public Follower(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
