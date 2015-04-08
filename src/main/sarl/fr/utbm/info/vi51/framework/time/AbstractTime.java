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

import java.util.concurrent.TimeUnit;

/** Abstract implementation of a time.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public abstract class AbstractTime implements Time {

	/**
	 */
	public AbstractTime() {
		//
	}

	@Override
	public float getCurrentTime(TimeUnit unit) {
		float t = getCurrentTime();
		switch(unit) {
		case DAYS:
			return t / 86400f;
		case HOURS:
			return t / 3600f;
		case MINUTES:
			return t / 60f;
		case SECONDS:
			return t;
		case MILLISECONDS:
			return t * 1000f;
		case MICROSECONDS:
			return t * 1000000f;
		case NANOSECONDS:
			return t * 1000000000f;
		default:
			return t;
		}
	}

	@Override
	public float getLastStepDuration(TimeUnit unit) {
		float step = getLastStepDuration();
		switch(unit) {
		case DAYS:
			return step / 86400f;
		case HOURS:
			return step / 3600f;
		case MINUTES:
			return step / 60f;
		case SECONDS:
			return step;
		case MILLISECONDS:
			return step * 1000f;
		case MICROSECONDS:
			return step * 1000000f;
		case NANOSECONDS:
			return step * 1000000000f;
		default:
			return step;
		}
	}

	@Override
	public float perSecond(float amountPerSecond) {
		return amountPerSecond * getLastStepDuration();
	}

	@Override
	public int compareTo(Time o) {
		float t = (o == null) ? 0f : o.getCurrentTime();
		return Float.compare(getCurrentTime(), t);
	}

}