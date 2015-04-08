/* 
 * $Id$
 * 
 * Copyright (C) 2007-2011 Stephane Galland <stephane.galland@utbm.fr>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.utbm.info.vi51.framework.environment;

import java.io.Serializable;

import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.util.LocalizedString;

/**
 * Abstract implementation of an object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public abstract class AbstractSituatedObject implements SituatedObject, Serializable {

	private static final long serialVersionUID = -3513920280954202791L;
	
	private Point2f position = new Point2f();
	private Shape2f<?> shape;
	private String name;
	private Serializable type;
	
	/**
	 * @param shape the shape of the body, considering that it is centered at the (0,0) position.
	 */
	public AbstractSituatedObject(Shape2f<?> shape) {
		this.shape = shape;
	}

	/**
	 * @param shape the shape of the body, considering that it is centered at the (0,0) position.
	 * @param position is the position of the object.
	 */
	public AbstractSituatedObject(Shape2f<?> shape, Point2f position) {
		assert(position!=null);
		this.shape = shape;
		this.position.set(position);
	}

	/**
	 * @param shape the shape of the body, considering that it is centered at the (0,0) position.
	 * @param x is the position of the object.
	 * @param y is the position of the object.
	 */
	public AbstractSituatedObject(Shape2f<?> shape, float x, float y) {
		this.shape = shape;
		this.position.set(x, y);
	}
	
	@Override
	public AbstractSituatedObject clone() {
		try {
			AbstractSituatedObject clone = (AbstractSituatedObject) super.clone();
			clone.position = this.position.clone();
			clone.shape = this.shape.clone();
			return clone;
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	@Override
	public Serializable getType() {
		return this.type;
	}
	
	/** Set the type of the object.
	 * 
	 * @param type
	 */
	public void setType(Serializable type) {
		this.type = type;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	/** Change the name of the object.
	 *
	 * The name is defined only for displaying purpose.
	 * 
	 * @return the name of the object.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Shape2f<?> getShape() {
		return this.shape.translate(getPosition());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point2f getPosition() {
		return new Point2f(this.position);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getX() {
		return this.position.getX();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getY() {
		return this.position.getY();
	}

	/**
	 * Set the position of the object.
	 * 
	 * @param x
	 * @param y
	 */
	protected void setPosition(float x, float y) {
		if (Double.isNaN(x) || Double.isNaN(y)) {
			System.err.println(LocalizedString.getString(getClass(), "INVALID_POSITION", getName()));
		} else {
			this.position.set(x, y);
		}
	}
	
	/**
	 * Move the position of the object.
	 * 
	 * @param x
	 * @param y
	 */
	protected void addPosition(float x, float y) {
		if (Double.isNaN(x) || Double.isNaN(y)) {
			System.err.println(LocalizedString.getString(getClass(), "INVALID_POSITION", getName()));
		} else {
			this.position.add(x, y);
		}
	}

	@Override
	public String toString() {
		String name = getName();
		if (name != null && !name.isEmpty()) {
			return name;
		}
		return super.toString();
	}

}