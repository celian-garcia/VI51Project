package fr.utbm.info.vi51.framework.agent;

import fr.utbm.info.vi51.framework.math.Vector2f;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Capacity;

@SuppressWarnings("all")
public interface PhysicEnvironment extends Capacity {
  /**
   * Default value for the parameter linearInfluence
   */
  @Generated(" null")
  public final static Vector2f ___FORMAL_PARAMETER_DEFAULT_VALUE_0_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @Generated(" 0f")
  public final static float ___FORMAL_PARAMETER_DEFAULT_VALUE_0_1 = 0f;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public abstract void influenceKinematic(@DefaultValue("0_0") final Vector2f linearInfluence, @DefaultValue("0_1") final float angularInfluence);
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public abstract void influenceKinematic();
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public abstract void influenceKinematic(final float angularInfluence);
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public abstract void influenceKinematic(final Vector2f linearInfluence);
  
  /**
   * Default value for the parameter linearInfluence
   */
  @Generated(" null")
  public final static Vector2f ___FORMAL_PARAMETER_DEFAULT_VALUE_1_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @Generated(" 0f")
  public final static float ___FORMAL_PARAMETER_DEFAULT_VALUE_1_1 = 0f;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public abstract void influenceSteering(@DefaultValue("1_0") final Vector2f linearInfluence, @DefaultValue("1_1") final float angularInfluence);
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public abstract void influenceSteering();
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public abstract void influenceSteering(final float angularInfluence);
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.vi51.framework.math.Vector2f,float")
  @Generated
  public abstract void influenceSteering(final Vector2f linearInfluence);
}
