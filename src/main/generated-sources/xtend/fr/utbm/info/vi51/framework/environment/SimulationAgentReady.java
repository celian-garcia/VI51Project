package fr.utbm.info.vi51.framework.environment;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * This event is fired when a simulation agent is ready.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 */
@SuppressWarnings("all")
public class SimulationAgentReady extends Event {
  /**
   * Construct an event. The source of the event is unknown.
   */
  @Generated
  public SimulationAgentReady() {
    super();
  }
  
  /**
   * Construct an event.
   * @param source - address of the agent that is emitting this event.
   */
  @Generated
  public SimulationAgentReady(final Address source) {
    super(source);
  }
  
  @Generated
  private final static long serialVersionUID = 588368462L;
}
