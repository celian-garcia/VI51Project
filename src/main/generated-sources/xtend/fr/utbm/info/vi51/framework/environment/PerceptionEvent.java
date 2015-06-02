package fr.utbm.info.vi51.framework.environment;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.time.TimePercept;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;
import java.util.List;

/**
 * This event contains percepts.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 */
@SuppressWarnings("all")
public class PerceptionEvent extends Event {
  public final Percept body;
  
  public final TimePercept time;
  
  public final List<Percept> perceptions;
  
  public PerceptionEvent(final List<Percept> p, final Percept b, final TimePercept t) {
    this.perceptions = p;
    this.body = b;
    this.time = t;
  }
  
  @Override
  @Generated
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    PerceptionEvent other = (PerceptionEvent) obj;
    if (this.body == null) {
      if (other.body != null)
        return false;
    } else if (!this.body.equals(other.body))
      return false;
    if (this.time == null) {
      if (other.time != null)
        return false;
    } else if (!this.time.equals(other.time))
      return false;
    if (this.perceptions == null) {
      if (other.perceptions != null)
        return false;
    } else if (!this.perceptions.equals(other.perceptions))
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.body== null) ? 0 : this.body.hashCode());
    result = prime * result + ((this.time== null) ? 0 : this.time.hashCode());
    result = prime * result + ((this.perceptions== null) ? 0 : this.perceptions.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the PerceptionEvent event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("body  = ").append(this.body);
    result.append("time  = ").append(this.time);
    result.append("perceptions  = ").append(this.perceptions);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = 3681047883L;
}
