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

/** Time manager for the Jaak environment.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public interface Time extends Comparable<Time> {

	/** Replies the current time in seconds.
	 *
	 * @return the current time in seconds.
	 */
	float getCurrentTime();

	/** Replies the current time in the given time unit.
	 *
	 * @param unit is the time unit to use for replied value.
	 * @return the current time.
	 */
	float getCurrentTime(TimeUnit unit);

	/** Replies the duration of the last simulation step in seconds.
	 *
	 * @return the duration of the last simulation step in seconds.
	 */
	float getLastStepDuration();

	/** Replies the duration of the last simulation step in the given time unit.
	 *
	 * @param unit is the time unit used to format the replied value.
	 * @return the duration of the last simulation step.
	 */
	float getLastStepDuration(TimeUnit unit);

	/** Replies the instant amount which is corresponds to the
	 * given amount, given per second.
	 * 
	 * @param amountPerSecond
	 * @return amountPerSecond * getTimeStepDuration()
	 */
	float perSecond(float amountPerSecond);

}