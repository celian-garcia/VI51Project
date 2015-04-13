package fr.utbm.info.vi51.marines.agent;

import com.google.common.base.Objects;
import fr.utbm.info.vi51.framework.agent.AbstractAnimat;
import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.PerceptionEvent;
import fr.utbm.info.vi51.framework.environment.SimulationAgentReady;
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.behavior.SeekBehaviour;
import fr.utbm.info.vi51.marines.behavior.WanderBehaviour;
import fr.utbm.info.vi51.marines.behavior.kinematic.KinematicSeekBehaviour;
import fr.utbm.info.vi51.marines.behavior.kinematic.KinematicWanderBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringAlignBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringFaceBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringSeekBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringWanderBehaviour;
import fr.utbm.info.vi51.marines.formation.Formation;
import io.sarl.core.AgentSpawned;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
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
import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("all")
public class Leader extends AbstractAnimat {
  protected SeekBehaviour seekBehaviour;
  
  protected WanderBehaviour wanderBehaviour;
  
  protected final float STOP_DISTANCE = 3f;
  
  protected final float STOP_RADIUS = (MathUtil.PI / 10f);
  
  protected final float SLOW_RADIUS = (MathUtil.PI / 4f);
  
  protected final float WANDER_CIRCLE_DISTANCE = 20f;
  
  protected final float WANDER_CIRCLE_RADIUS = 10f;
  
  protected final float WANDER_MAX_ROTATION = (MathUtil.PI / 4f);
  
  protected Formation formation;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
    Object _get = occurrence.parameters[3];
    this.formation = ((Formation) _get);
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      SteeringSeekBehaviour _steeringSeekBehaviour = new SteeringSeekBehaviour();
      this.seekBehaviour = _steeringSeekBehaviour;
      SteeringAlignBehaviour alignB = new SteeringAlignBehaviour(this.STOP_RADIUS, this.SLOW_RADIUS);
      SteeringFaceBehaviour faceB = new SteeringFaceBehaviour(this.STOP_DISTANCE, alignB);
      SteeringWanderBehaviour _steeringWanderBehaviour = new SteeringWanderBehaviour(
        this.WANDER_CIRCLE_DISTANCE, 
        this.WANDER_CIRCLE_RADIUS, 
        this.WANDER_MAX_ROTATION, faceB);
      this.wanderBehaviour = _steeringWanderBehaviour;
    } else {
      KinematicSeekBehaviour _kinematicSeekBehaviour = new KinematicSeekBehaviour();
      this.seekBehaviour = _kinematicSeekBehaviour;
      KinematicWanderBehaviour _kinematicWanderBehaviour = new KinematicWanderBehaviour();
      this.wanderBehaviour = _kinematicWanderBehaviour;
    }
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    this.emit(_simulationAgentReady);
  }
  
  @Generated
  private boolean _guard_PerceptionEvent_1(final PerceptionEvent occurrence) {
    boolean _isEmpty = occurrence.perceptions.isEmpty();
    return _isEmpty;
  }
  
  @Percept
  public void _handle_PerceptionEvent_1(final PerceptionEvent occurrence) {
    if (_guard_PerceptionEvent_1(occurrence)) {
      Point2f _position = occurrence.body.getPosition();
      this.formation.setGlobalPosition(_position);
      Vector2f _direction = occurrence.body.getDirection();
      this.formation.setGlobalOrientation(_direction);
      Point2f _position_1 = occurrence.body.getPosition();
      Vector2f _direction_1 = occurrence.body.getDirection();
      float _currentLinearSpeed = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear = this.getMaxLinear(occurrence.body);
      float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position_1, _direction_1, _currentLinearSpeed, _maxLinear, _currentAngularSpeed, _maxAngular);
      this.emitInfluence(_runWander);
    }
  }
  
  @Generated
  private boolean _guard_PerceptionEvent_2(final PerceptionEvent occurrence) {
    boolean _isEmpty = occurrence.perceptions.isEmpty();
    boolean _not = (!_isEmpty);
    return _not;
  }
  
  @Percept
  public void _handle_PerceptionEvent_2(final PerceptionEvent occurrence) {
    if (_guard_PerceptionEvent_2(occurrence)) {
      Point2f _position = occurrence.body.getPosition();
      this.formation.setGlobalPosition(_position);
      Vector2f _direction = occurrence.body.getDirection();
      this.formation.setGlobalOrientation(_direction);
      fr.utbm.info.vi51.framework.environment.Percept firstPercept = occurrence.perceptions.get(0);
      Serializable _type = firstPercept.getType();
      boolean _equals = Objects.equal(_type, "TARGET");
      if (_equals) {
        Point2f _position_1 = firstPercept.getPosition();
        Point2f target = _position_1.clone();
        Point2f _position_2 = occurrence.body.getPosition();
        float _currentLinearSpeed = occurrence.body.getCurrentLinearSpeed();
        float _maxLinear = this.getMaxLinear(occurrence.body);
        BehaviourOutput bo1 = this.seekBehaviour.runSeek(_position_2, _currentLinearSpeed, _maxLinear, target);
        Vector2f _linear = bo1.getLinear();
        _linear.normalize();
        Vector2f v = bo1.getLinear();
        for (final fr.utbm.info.vi51.framework.environment.Percept obj : occurrence.perceptions) {
          Serializable _type_1 = obj.getType();
          boolean _equals_1 = Objects.equal(_type_1, "ROCK");
          if (_equals_1) {
            Point2f _position_3 = obj.getPosition();
            Point2f _position_4 = occurrence.body.getPosition();
            Vector2f rv = this.repulsiveVector(_position_3, _position_4);
            v.add(rv);
          }
        }
        v.normalize();
        float _maxLinear_1 = this.getMaxLinear(occurrence.body);
        v.scale(_maxLinear_1);
        bo1.setLinear(v);
        this.emitInfluence(bo1);
      } else {
        Point2f _position_5 = occurrence.body.getPosition();
        Vector2f _direction_1 = occurrence.body.getDirection();
        float _currentLinearSpeed_1 = occurrence.body.getCurrentLinearSpeed();
        float _maxLinear_2 = this.getMaxLinear(occurrence.body);
        float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
        float _maxAngular = this.getMaxAngular(occurrence.body);
        BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position_5, _direction_1, _currentLinearSpeed_1, _maxLinear_2, _currentAngularSpeed, _maxAngular);
        this.emitInfluence(_runWander);
      }
    }
  }
  
  public Vector2f repulsiveVector(final Point2f obj, final Point2f me) {
    Vector2f v = new Vector2f();
    v.sub(me, obj);
    float dist = v.length();
    this.println(Float.valueOf(dist));
    v.normalize();
    v.scale((1 / dist));
    return v;
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
   * See the capacity {@link io.sarl.core.Logging#debug(java.lang.Object)}.
   * 
   * @see io.sarl.core.Logging#debug(java.lang.Object)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void debug(final Object message) {
    getSkill(io.sarl.core.Logging.class).debug(message);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#error(java.lang.Object)}.
   * 
   * @see io.sarl.core.Logging#error(java.lang.Object)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void error(final Object message) {
    getSkill(io.sarl.core.Logging.class).error(message);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#error(java.lang.Object,java.lang.Throwable)}.
   * 
   * @see io.sarl.core.Logging#error(java.lang.Object,java.lang.Throwable)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void error(final Object message, final Throwable exception) {
    getSkill(io.sarl.core.Logging.class).error(message, exception);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#getLogLevel()}.
   * 
   * @see io.sarl.core.Logging#getLogLevel()
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected int getLogLevel() {
    return getSkill(io.sarl.core.Logging.class).getLogLevel();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#info(java.lang.Object)}.
   * 
   * @see io.sarl.core.Logging#info(java.lang.Object)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void info(final Object message) {
    getSkill(io.sarl.core.Logging.class).info(message);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#isDebugLogEnabled()}.
   * 
   * @see io.sarl.core.Logging#isDebugLogEnabled()
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected boolean isDebugLogEnabled() {
    return getSkill(io.sarl.core.Logging.class).isDebugLogEnabled();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#isErrorLogEnabled()}.
   * 
   * @see io.sarl.core.Logging#isErrorLogEnabled()
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected boolean isErrorLogEnabled() {
    return getSkill(io.sarl.core.Logging.class).isErrorLogEnabled();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#isInfoLogEnabled()}.
   * 
   * @see io.sarl.core.Logging#isInfoLogEnabled()
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected boolean isInfoLogEnabled() {
    return getSkill(io.sarl.core.Logging.class).isInfoLogEnabled();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#isWarningLogEnabled()}.
   * 
   * @see io.sarl.core.Logging#isWarningLogEnabled()
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected boolean isWarningLogEnabled() {
    return getSkill(io.sarl.core.Logging.class).isWarningLogEnabled();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#println(java.lang.Object)}.
   * 
   * @see io.sarl.core.Logging#println(java.lang.Object)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void println(final Object message) {
    getSkill(io.sarl.core.Logging.class).println(message);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#setLogLevel(int)}.
   * 
   * @see io.sarl.core.Logging#setLogLevel(int)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void setLogLevel(final int level) {
    getSkill(io.sarl.core.Logging.class).setLogLevel(level);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#setLoggingName(java.lang.String)}.
   * 
   * @see io.sarl.core.Logging#setLoggingName(java.lang.String)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void setLoggingName(final String message) {
    getSkill(io.sarl.core.Logging.class).setLoggingName(message);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#warning(java.lang.Object)}.
   * 
   * @see io.sarl.core.Logging#warning(java.lang.Object)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void warning(final Object message) {
    getSkill(io.sarl.core.Logging.class).warning(message);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Logging#warning(java.lang.Object,java.lang.Throwable)}.
   * 
   * @see io.sarl.core.Logging#warning(java.lang.Object,java.lang.Throwable)
   */
  @Generated
  @ImportedCapacityFeature(Logging.class)
  protected void warning(final Object message, final Throwable exception) {
    getSkill(io.sarl.core.Logging.class).warning(message, exception);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public Leader(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public Leader(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
