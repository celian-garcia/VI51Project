package fr.utbm.info.vi51.marines.agent;

import com.google.common.base.Objects;
import fr.utbm.info.vi51.framework.agent.AbstractOrganized;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.marines.behavior.AlignBehaviour;
import fr.utbm.info.vi51.marines.behavior.SeekBehaviour;
import fr.utbm.info.vi51.marines.behavior.WanderBehaviour;
import fr.utbm.info.vi51.marines.behavior.kinematic.KinematicAlignBehaviour;
import fr.utbm.info.vi51.marines.behavior.kinematic.KinematicSeekBehaviour;
import fr.utbm.info.vi51.marines.behavior.kinematic.KinematicWanderBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringAlignBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringFaceBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringSeekBehaviour;
import fr.utbm.info.vi51.marines.behavior.steering.SteeringWanderBehaviour;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Percept;
import java.util.UUID;

@SuppressWarnings("all")
public class AbstractCommander extends AbstractOrganized {
  protected SeekBehaviour seekBehaviour;
  
  protected AlignBehaviour alignBehaviour;
  
  protected WanderBehaviour wanderBehaviour;
  
  protected final float STOP_DISTANCE = 3f;
  
  protected final float STOP_RADIUS = (MathUtil.PI / 10f);
  
  protected final float SLOW_RADIUS = (MathUtil.PI / 4f);
  
  protected final float WANDER_CIRCLE_DISTANCE = 20f;
  
  protected final float WANDER_CIRCLE_RADIUS = 10f;
  
  protected final float WANDER_MAX_ROTATION = (MathUtil.PI / 4f);
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
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
      KinematicSeekBehaviour _kinematicSeekBehaviour = new KinematicSeekBehaviour(5.0f);
      this.seekBehaviour = _kinematicSeekBehaviour;
      KinematicAlignBehaviour _kinematicAlignBehaviour = new KinematicAlignBehaviour(this.STOP_RADIUS);
      this.alignBehaviour = _kinematicAlignBehaviour;
      KinematicWanderBehaviour _kinematicWanderBehaviour = new KinematicWanderBehaviour();
      this.wanderBehaviour = _kinematicWanderBehaviour;
    }
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public AbstractCommander(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public AbstractCommander(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
