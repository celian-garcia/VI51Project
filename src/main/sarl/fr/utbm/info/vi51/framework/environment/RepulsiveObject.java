package fr.utbm.info.vi51.framework.environment;

import java.util.UUID;

//import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;

public class RepulsiveObject extends AbstractSituatedObject {

	private static final long serialVersionUID = 1286955090621696919L;

	private final UUID id;
	
	/**
	 * @param shape the shape of the body, considering that it is centered at the (0,0) position.
	 * @param position is the position of the object.
	 */
	public RepulsiveObject(UUID id, Shape2f<?> shape, float k_strength, float s_strength) {
		super(shape);
		this.id = id;
	}
	
	@Override
	public RepulsiveObject clone() {
		RepulsiveObject clone = (RepulsiveObject) super.clone();
		return clone;
	}
	
	/** Replies the id of this object.
	 * 
	 * @return the id of this object.
	 */
	public UUID getID() {
		return this.id;
	}

}
