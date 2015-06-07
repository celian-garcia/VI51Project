package fr.utbm.info.vi51.marines.agent;

import com.google.common.base.Objects;
import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.PerceptionEvent;
import fr.utbm.info.vi51.framework.environment.SimulationAgentReady;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.agent.AbstractCommander;
import fr.utbm.info.vi51.marines.formation.FormationSlot;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author celian
 */
@SuppressWarnings("all")
public class Leader extends AbstractCommander {
  protected FormationSlot slot;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
    ArrayList<Object> list = new ArrayList<Object>();
    Object _get = occurrence.parameters[3];
    list.add(_get);
    int index = this.initializeFollowedFormation(list);
    FormationSlot _slotAt = this.followedFormation.getSlotAt(index);
    this.slot = _slotAt;
    Object _get_1 = occurrence.parameters[(4 + index)];
    this.initializeOwnFormation(_get_1);
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    this.emit(_simulationAgentReady);
  }
  
  @Percept
  public void _handle_PerceptionEvent_1(final PerceptionEvent occurrence) {
    int index = this.updateFollowedFormation(occurrence.formations);
    if ((index != (-1))) {
      FormationSlot _slotAt = this.followedFormation.getSlotAt(index);
      this.slot = _slotAt;
    }
    Point2f _position = occurrence.body.getPosition();
    this.ownFormation.setGlobalPosition(_position);
    Vector2f _direction = occurrence.body.getDirection();
    this.ownFormation.setGlobalOrientation(_direction);
    if ((this.slot == null)) {
      Point2f _position_1 = occurrence.body.getPosition();
      Vector2f _direction_1 = occurrence.body.getDirection();
      float _currentLinearSpeed = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear = this.getMaxLinear(occurrence.body);
      float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position_1, _direction_1, _currentLinearSpeed, _maxLinear, _currentAngularSpeed, _maxAngular);
      this.emitInfluence(_runWander);
    } else {
      Point2f target = this.slot.getGlobalPosition();
      Point2f _position_2 = occurrence.body.getPosition();
      float _currentLinearSpeed_1 = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear_1 = this.getMaxLinear(occurrence.body);
      BehaviourOutput bo1 = this.seekBehaviour.runSeek(_position_2, _currentLinearSpeed_1, _maxLinear_1, target);
      Vector2f v = bo1.getLinear();
      v.normalize();
      for (final fr.utbm.info.vi51.framework.environment.Percept obj : occurrence.perceptions) {
        Serializable _type = obj.getType();
        boolean _equals = Objects.equal(_type, "ROCK");
        if (_equals) {
          Point2f _position_3 = obj.getPosition();
          Shape2f<?> _shape = obj.getShape();
          Rectangle2f _bounds = _shape.getBounds();
          Point2f _position_4 = occurrence.body.getPosition();
          Shape2f<?> _shape_1 = occurrence.body.getShape();
          Rectangle2f _bounds_1 = _shape_1.getBounds();
          float dmin = this.computeDistanceMin(_position_3, _bounds, _position_4, _bounds_1);
          Point2f _position_5 = obj.getPosition();
          Point2f _position_6 = occurrence.body.getPosition();
          Vector2f rv = this.repulsiveVector(_position_5, _position_6, dmin);
          v.add(rv);
        }
      }
      v.normalize();
      float _maxLinear_2 = this.getMaxLinear(occurrence.body);
      v.scale(_maxLinear_2);
      bo1.setLinear(v);
      Vector2f direction = new Vector2f();
      Point2f _position_7 = occurrence.body.getPosition();
      direction.sub(_position_7, target);
      float _length = direction.length();
      boolean _lessThan = (_length < 5.0f);
      if (_lessThan) {
        Vector2f orientation = this.slot.getGlobalOrientation();
        Vector2f _direction_2 = occurrence.body.getDirection();
        float _currentAngularSpeed_1 = occurrence.body.getCurrentAngularSpeed();
        float _maxAngular_1 = this.getMaxAngular(occurrence.body);
        BehaviourOutput _runAlign = this.alignBehaviour.runAlign(_direction_2, _currentAngularSpeed_1, _maxAngular_1, orientation);
        bo1.setAngular(_runAlign);
      } else {
        Vector2f _direction_3 = occurrence.body.getDirection();
        float _currentAngularSpeed_2 = occurrence.body.getCurrentAngularSpeed();
        float _maxAngular_2 = this.getMaxAngular(occurrence.body);
        BehaviourOutput _runAlign_1 = this.alignBehaviour.runAlign(_direction_3, _currentAngularSpeed_2, _maxAngular_2, direction);
        bo1.setAngular(_runAlign_1);
      }
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
