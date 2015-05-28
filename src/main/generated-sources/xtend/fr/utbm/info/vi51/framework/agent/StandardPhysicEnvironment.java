package fr.utbm.info.vi51.framework.agent;

import fr.utbm.info.vi51.framework.agent.PhysicEnvironment;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.InfluenceEvent;
import fr.utbm.info.vi51.framework.environment.KillInfluence;
import fr.utbm.info.vi51.framework.environment.MotionInfluence;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.util.AddressUUIDScope;
import io.sarl.core.AgentSpawned;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.FiredEvent;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventListener;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.core.Space;
import io.sarl.lang.core.SpaceID;
import io.sarl.util.OpenEventSpace;
import java.util.UUID;

@SuppressWarnings("all")
public class StandardPhysicEnvironment extends Skill implements PhysicEnvironment {
  protected final UUID spaceID;
  
  protected final UUID environmentID;
  
  protected OpenEventSpace physicSpace;
  
  protected Address myAdr;
  
  public StandardPhysicEnvironment(final UUID spaceID, final UUID environmentID) {
    this.environmentID = environmentID;
    this.spaceID = spaceID;
  }
  
  public void install() {
    do {
      {
        AgentContext _defaultContext = this.getDefaultContext();
        OpenEventSpace _space = _defaultContext.<OpenEventSpace>getSpace(this.spaceID);
        this.physicSpace = _space;
        Thread.yield();
      }
    } while((this.physicSpace == null));
    EventListener _asEventListener = this.asEventListener();
    this.physicSpace.register(_asEventListener);
    Agent _owner = this.getOwner();
    UUID _iD = _owner.getID();
    Address _address = this.physicSpace.getAddress(_iD);
    this.myAdr = _address;
  }
  
  public void uninstall() {
    KillInfluence _killInfluence = new KillInfluence();
    InfluenceEvent event = new InfluenceEvent(_killInfluence);
    event.setSource(this.myAdr);
    AddressUUIDScope _addressUUIDScope = new AddressUUIDScope(this.environmentID);
    this.physicSpace.emit(event, _addressUUIDScope);
    this.physicSpace = null;
  }
  
  /**
   * Default value for the parameter linearInfluence
   */
  @Generated(" null")
  private final static Vector2f ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCEKINEMATIC_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @Generated(" 0f")
  private final static float ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCEKINEMATIC_1 = 0f;
  
  @DefaultValueSource
  public void influenceKinematic(@DefaultValue("fr.utbm.info.vi51.framework.agent.StandardPhysicEnvironment#INFLUENCEKINEMATIC_0") final Vector2f linearInfluence, @DefaultValue("fr.utbm.info.vi51.framework.agent.StandardPhysicEnvironment#INFLUENCEKINEMATIC_1") final float angularInfluence) {
    MotionInfluence mi = null;
    boolean _tripleEquals = linearInfluence.operator_tripleEquals(null);
    if (_tripleEquals) {
      MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, angularInfluence);
      mi = _motionInfluence;
    } else {
      MotionInfluence _motionInfluence_1 = new MotionInfluence(DynamicType.KINEMATIC, linearInfluence, angularInfluence);
      mi = _motionInfluence_1;
    }
    InfluenceEvent event = new InfluenceEvent(mi);
    event.setSource(this.myAdr);
    AddressUUIDScope _addressUUIDScope = new AddressUUIDScope(this.environmentID);
    this.physicSpace.emit(event, _addressUUIDScope);
  }
  
  /**
   * Default value for the parameter linearInfluence
   */
  @Generated(" null")
  private final static Vector2f ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCESTEERING_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @Generated(" 0f")
  private final static float ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCESTEERING_1 = 0f;
  
  @DefaultValueSource
  public void influenceSteering(@DefaultValue("fr.utbm.info.vi51.framework.agent.StandardPhysicEnvironment#INFLUENCESTEERING_0") final Vector2f linearInfluence, @DefaultValue("fr.utbm.info.vi51.framework.agent.StandardPhysicEnvironment#INFLUENCESTEERING_1") final float angularInfluence) {
    MotionInfluence mi = null;
    boolean _tripleEquals = linearInfluence.operator_tripleEquals(null);
    if (_tripleEquals) {
      MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.STEERING, angularInfluence);
      mi = _motionInfluence;
    } else {
      MotionInfluence _motionInfluence_1 = new MotionInfluence(DynamicType.STEERING, linearInfluence, angularInfluence);
      mi = _motionInfluence_1;
    }
    InfluenceEvent event = new InfluenceEvent(mi);
    event.setSource(this.myAdr);
    AddressUUIDScope _addressUUIDScope = new AddressUUIDScope(this.environmentID);
    this.physicSpace.emit(event, _addressUUIDScope);
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
   * See the capacity {@link io.sarl.core.Behaviors#asEventListener()}.
   * 
   * @see io.sarl.core.Behaviors#asEventListener()
   */
  @Generated
  @ImportedCapacityFeature(Behaviors.class)
  protected EventListener asEventListener() {
    return getSkill(io.sarl.core.Behaviors.class).asEventListener();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Behaviors#registerBehavior(io.sarl.lang.core.Behavior)}.
   * 
   * @see io.sarl.core.Behaviors#registerBehavior(io.sarl.lang.core.Behavior)
   */
  @Generated
  @ImportedCapacityFeature(Behaviors.class)
  protected Behavior registerBehavior(final Behavior attitude) {
    return getSkill(io.sarl.core.Behaviors.class).registerBehavior(attitude);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Behaviors#unregisterBehavior(io.sarl.lang.core.Behavior)}.
   * 
   * @see io.sarl.core.Behaviors#unregisterBehavior(io.sarl.lang.core.Behavior)
   */
  @Generated
  @ImportedCapacityFeature(Behaviors.class)
  protected Behavior unregisterBehavior(final Behavior attitude) {
    return getSkill(io.sarl.core.Behaviors.class).unregisterBehavior(attitude);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Behaviors#wake(io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.Behaviors#wake(io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(Behaviors.class)
  protected void wake(final Event evt) {
    getSkill(io.sarl.core.Behaviors.class).wake(evt);
  }
  
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public final void influenceKinematic() {
    influenceKinematic(___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCEKINEMATIC_0, ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCEKINEMATIC_1);
  }
  
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public final void influenceKinematic(final float angularInfluence) {
    influenceKinematic(___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCEKINEMATIC_0, angularInfluence);
  }
  
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public final void influenceKinematic(final Vector2f linearInfluence) {
    influenceKinematic(linearInfluence, ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCEKINEMATIC_1);
  }
  
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public final void influenceSteering() {
    influenceSteering(___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCESTEERING_0, ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCESTEERING_1);
  }
  
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public final void influenceSteering(final float angularInfluence) {
    influenceSteering(___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCESTEERING_0, angularInfluence);
  }
  
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public final void influenceSteering(final Vector2f linearInfluence) {
    influenceSteering(linearInfluence, ___FORMAL_PARAMETER_DEFAULT_VALUE_INFLUENCESTEERING_1);
  }
}
