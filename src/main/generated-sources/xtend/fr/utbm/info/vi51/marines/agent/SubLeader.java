package fr.utbm.info.vi51.marines.agent;

import com.google.common.base.Objects;
import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.PerceptionEvent;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.marines.agent.Leader;
import fr.utbm.info.vi51.marines.formation.Formation;
import fr.utbm.info.vi51.marines.formation.FormationAssignment;
import fr.utbm.info.vi51.marines.formation.FormationSlot;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Percept;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author celian
 */
@SuppressWarnings("all")
public class SubLeader extends Leader {
  protected FormationSlot chiefFormationSlot;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
    Object _get = occurrence.parameters[5];
    Formation chiefFormation = ((Formation) _get);
    Object _get_1 = occurrence.parameters[6];
    FormationAssignment assignment = ((FormationAssignment) _get_1);
    int index = assignment.allocate(false);
    FormationSlot _slotAt = chiefFormation.getSlotAt(index);
    this.chiefFormationSlot = _slotAt;
  }
  
  @Percept
  public void _handle_PerceptionEvent_1(final PerceptionEvent occurrence) {
    if ((this.chiefFormationSlot == null)) {
      Point2f _position = occurrence.body.getPosition();
      Vector2f _direction = occurrence.body.getDirection();
      float _currentLinearSpeed = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear = this.getMaxLinear(occurrence.body);
      float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position, _direction, _currentLinearSpeed, _maxLinear, _currentAngularSpeed, _maxAngular);
      this.emitInfluence(_runWander);
    } else {
      Point2f position = this.chiefFormationSlot.getGlobalPosition();
      Point2f _position_1 = occurrence.body.getPosition();
      float _currentLinearSpeed_1 = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear_1 = this.getMaxLinear(occurrence.body);
      BehaviourOutput bo1 = this.seekBehaviour.runSeek(_position_1, _currentLinearSpeed_1, _maxLinear_1, position);
      Vector2f v = bo1.getLinear();
      v.normalize();
      for (final fr.utbm.info.vi51.framework.environment.Percept obj : occurrence.perceptions) {
        Serializable _type = obj.getType();
        boolean _equals = Objects.equal(_type, "ROCK");
        if (_equals) {
          Point2f _position_2 = obj.getPosition();
          Shape2f<?> _shape = obj.getShape();
          Rectangle2f _bounds = _shape.getBounds();
          Point2f _position_3 = occurrence.body.getPosition();
          Shape2f<?> _shape_1 = occurrence.body.getShape();
          Rectangle2f _bounds_1 = _shape_1.getBounds();
          float dmin = this.computeDistanceMin(_position_2, _bounds, _position_3, _bounds_1);
          Point2f _position_4 = obj.getPosition();
          Point2f _position_5 = occurrence.body.getPosition();
          Vector2f rv = this.repulsiveVector(_position_4, _position_5, dmin);
          v.add(rv);
        }
      }
      v.normalize();
      float _maxLinear_2 = this.getMaxLinear(occurrence.body);
      v.scale(_maxLinear_2);
      bo1.setLinear(v);
      Vector2f orientation = this.chiefFormationSlot.getGlobalOrientation();
      Vector2f _direction_1 = occurrence.body.getDirection();
      float _currentAngularSpeed_1 = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular_1 = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runAlign = this.alignBehaviour.runAlign(_direction_1, _currentAngularSpeed_1, _maxAngular_1, orientation);
      bo1.setAngular(_runAlign);
      this.emitInfluence(bo1);
    }
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public SubLeader(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public SubLeader(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
