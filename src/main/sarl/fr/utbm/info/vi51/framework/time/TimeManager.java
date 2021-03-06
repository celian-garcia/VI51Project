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


/** Time manager for the Jaak environment.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public interface TimeManager extends Time {

	/** Increment the time.
	 */
	void increment();
	
	/** Replies the delay to apply at each simulation step.
	 *
	 * This delay permits to control partially the speed of the simulation process.
	 *
	 * @return the delay of waiting between two simulation steps.
	 */
	float getSimulationDelay();

	/** Set the delay to apply at each simulation step.
	 *
	 * This delay permits to control partially the speed of the simulation process.
	 *
	 * @param delay the delay of waiting between two simulation steps.
	 */
	void setSimulationDelay(float delay);

}