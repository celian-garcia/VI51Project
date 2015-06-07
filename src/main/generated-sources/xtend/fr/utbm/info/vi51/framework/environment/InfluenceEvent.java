package fr.utbm.info.vi51.framework.environment;

import fr.utbm.info.vi51.framework.environment.Influence;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

/**
 * This event contains an influence
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 */
@SuppressWarnings("all")
public class InfluenceEvent extends Event {
  public final Influence influence;
  
  public InfluenceEvent(final Influence e) {
    this.influence = e;
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
    InfluenceEvent other = (InfluenceEvent) obj;
    if (this.influence == null) {
      if (other.influence != null)
        return false;
    } else if (!this.influence.equals(other.influence))
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.influence== null) ? 0 : this.influence.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the InfluenceEvent event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("influence  = ").append(this.influence);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = -41764013L;
}
