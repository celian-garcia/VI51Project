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


/** Step-based Time manager.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class StepTimeManager extends AbstractTimeManager {

	private float delay = 10;
	private float t;
	private final float step;
	
	/**
	 * @param stepDuration the duration of one simulation step (in ms)
	 */
	public StepTimeManager(float stepDuration) {
		this.step = stepDuration / 1000f;
	}

	@Override
	public synchronized float getCurrentTime() {
		return this.t;
	}

	@Override
	public synchronized float getLastStepDuration() {
		return this.step;
	}

	@Override
	public synchronized void increment() {
		this.t += this.step;
	}

	@Override
	public synchronized float getSimulationDelay() {
		return this.delay;
	}

	@Override
	public synchronized void setSimulationDelay(float delay) {
		this.delay = Math.max(0f, delay);
	}

}