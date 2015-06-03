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
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Percept;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author celian
 */
@SuppressWarnings("all")
public class LeaderChief extends Leader {
  protected Formation chiefFormation;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    Object _get = occurrence.parameters[3];
    this.chiefFormation = ((Formation) _get);
    this.fIndex = 0;
    super._handle_Initialize_0(occurrence);
  }
  
  @Generated
  private boolean _guard_PerceptionEvent_1(final PerceptionEvent occurrence) {
    boolean _isEmpty = occurrence.perceptions.isEmpty();
    return _isEmpty;
  }
  
  @Percept
  public void _handle_PerceptionEvent_1(final PerceptionEvent occurrence) {
    if (_guard_PerceptionEvent_1(occurrence)) {
      super._handle_PerceptionEvent_1(occurrence);
      boolean _isEmpty = occurrence.formations.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        Formation _get = occurrence.formations.get(0);
        this.chiefFormation = _get;
      }
      Point2f _position = occurrence.body.getPosition();
      this.chiefFormation.setGlobalPosition(_position);
      Vector2f _direction = occurrence.body.getDirection();
      this.chiefFormation.setGlobalOrientation(_direction);
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
      super._handle_PerceptionEvent_1(occurrence);
      Point2f _position = occurrence.body.getPosition();
      this.chiefFormation.setGlobalPosition(_position);
      Vector2f _direction = occurrence.body.getDirection();
      this.chiefFormation.setGlobalOrientation(_direction);
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
        Vector2f v = bo1.getLinear();
        v.normalize();
        for (final fr.utbm.info.vi51.framework.environment.Percept obj : occurrence.perceptions) {
          Serializable _type_1 = obj.getType();
          boolean _equals_1 = Objects.equal(_type_1, "ROCK");
          if (_equals_1) {
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
        float _maxLinear_1 = this.getMaxLinear(occurrence.body);
        v.scale(_maxLinear_1);
        bo1.setLinear(v);
        this.emitInfluence(bo1);
      } else {
        Point2f _position_7 = occurrence.body.getPosition();
        Vector2f _direction_1 = occurrence.body.getDirection();
        float _currentLinearSpeed_1 = occurrence.body.getCurrentLinearSpeed();
        float _maxLinear_2 = this.getMaxLinear(occurrence.body);
        float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
        float _maxAngular = this.getMaxAngular(occurrence.body);
        BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position_7, _direction_1, _currentLinearSpeed_1, _maxLinear_2, _currentAngularSpeed, _maxAngular);
        this.emitInfluence(_runWander);
      }
    }
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public LeaderChief(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public LeaderChief(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
