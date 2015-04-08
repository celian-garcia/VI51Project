/*
 * $Id$
 *
 * Jaak environment model is an open-source multiagent library.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014 Stéphane GALLAND.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.utbm.info.vi51.framework.time;

import java.io.Serializable;

/** A perception of the time.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class TimePercept extends AbstractTime implements Serializable, Cloneable {

	private static final long serialVersionUID = 6751639163169606473L;

	private final float currentTime;
	private final float stepDuration;
	
	/**
	 * @param currentTime
	 * @param stepDuration
	 */
	public TimePercept(float currentTime, float stepDuration) {
		this.currentTime = currentTime;
		this.stepDuration = stepDuration;
	}

	@Override
	public float getCurrentTime() {
		return this.currentTime;
	}

	@Override
	public float getLastStepDuration() {
		return this.stepDuration;
	}
	
	@Override
	public TimePercept clone() {
		try {
			return (TimePercept) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Time) {
			Time t = (Time) obj;
			return this.currentTime == t.getCurrentTime();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Float.valueOf(this.currentTime).hashCode();
	}
	
	@Override
	public String toString() {
		return Float.toString(this.currentTime);
	}

}