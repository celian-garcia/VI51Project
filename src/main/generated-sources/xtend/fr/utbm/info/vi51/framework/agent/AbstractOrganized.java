package fr.utbm.info.vi51.framework.agent;

import fr.utbm.info.vi51.framework.agent.AbstractAnimat;
import fr.utbm.info.vi51.marines.formation.Formation;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Percept;
import java.util.List;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class AbstractOrganized extends AbstractAnimat {
  /**
   * The own formation
   */
  protected Formation ownFormation;
  
  /**
   * The own master formation
   */
  protected Formation ownMasterFormation;
  
  /**
   * The followed formation
   */
  protected Formation followedFormation;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
    this.ownFormation = ((Formation) null);
    this.ownMasterFormation = ((Formation) null);
    this.followedFormation = ((Formation) null);
  }
  
  public int initializeFollowedFormation(final List<Object> formations) {
    int i = 0;
    int index = (-1);
    Formation formation = ((Formation) null);
    while (((index == (-1)) && (i < ((Object[])Conversions.unwrapArray(formations, Object.class)).length))) {
      {
        int _plusPlus = i++;
        Object _get = formations.get(_plusPlus);
        formation = ((Formation) _get);
        int _allocate = formation.allocate(false);
        index = _allocate;
      }
    }
    if ((index != (-1))) {
      this.followedFormation = formation;
    }
    return index;
  }
  
  public void initializeOwnFormation(final List<Object> formations) {
    int i = 0;
    boolean found = false;
    Formation formation = ((Formation) null);
    while (((!found) && (i < ((Object[])Conversions.unwrapArray(formations, Object.class)).length))) {
      {
        int _plusPlus = i++;
        Object _get = formations.get(_plusPlus);
        formation = ((Formation) _get);
        boolean _haveLeader = formation.haveLeader();
        boolean _not = (!_haveLeader);
        if (_not) {
          found = true;
        }
      }
    }
    if (found) {
      this.ownFormation = formation;
    }
  }
  
  public void initializeOwnFormation(final Object formation) {
    this.ownFormation = ((Formation) formation);
  }
  
  public void initializeOwnMasterFormation(final List<Object> formations) {
    int i = 0;
    boolean found = false;
    Formation formation = ((Formation) null);
    while (((!found) && (i < ((Object[])Conversions.unwrapArray(formations, Object.class)).length))) {
      {
        int _plusPlus = i++;
        Object _get = formations.get(_plusPlus);
        formation = ((Formation) _get);
        boolean _haveLeader = formation.haveLeader();
        boolean _not = (!_haveLeader);
        if (_not) {
          found = true;
        }
      }
    }
    if (found) {
      this.ownMasterFormation = formation;
    }
  }
  
  public void initializeOwnMasterFormation(final Object formation) {
    this.ownMasterFormation = ((Formation) formation);
  }
  
  public void updateFollowedFormation(final Formation formation) {
  }
  
  public void updateOwnFormation(final Formation formation) {
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public AbstractOrganized(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public AbstractOrganized(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
