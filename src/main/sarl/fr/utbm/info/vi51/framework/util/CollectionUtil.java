/* 
 * $Id$
 * 
 * Copyright (C) 2010-2011 Janus Core Developers
 * Copyright (C) 2012 Stéphane GALLAND
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
package fr.utbm.info.vi51.framework.util;

import java.util.Iterator;
import java.util.NoSuchElementException;


/** Utilities on collections.
 * 
 * @author $Author: galland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public final class CollectionUtil {

	/** Create an iterable with the given elements.
	 *
	 * @param iterable - elements.
	 * @param objects - elements.
	 * @return the iterable.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Iterable<T> newIterable(Iterable<? extends T> iterable, T... objects) {
		return new Iterable1<T>(iterable, objects);
	}
	
	/**
	 * @author $Author: galland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	private static class Iterable1<T> implements Iterable<T> {

		private final Iterable<? extends T> iterable;
		private final T[] objects;

		/**
		 * @param iterable - elements.
		 * @param objects - elements.
		 */
		@SuppressWarnings("unchecked")
		public Iterable1(Iterable<? extends T> iterable, T... objects) {
			this.iterable = iterable;
			this.objects = objects;
		}

		@Override
		public Iterator<T> iterator() {
			return new Iterator1<T>(iterable, objects);
		}
		
		/**
		 * @author $Author: galland$
		 * @version $FullVersion$
		 * @mavengroupid $GroupId$
		 * @mavenartifactid $ArtifactId$
		 */
		private static class Iterator1<T> implements Iterator<T> {

			private final Iterator<? extends T> iterator;
			private final T[] objects;
			private int index = 0;
			
			/**
			 * @param iterable - elements.
			 * @param objects - elements.
			 */
			@SuppressWarnings("unchecked")
			public Iterator1(Iterable<? extends T> iterable, T... objects) {
				this.iterator = iterable.iterator();
				this.objects = objects;
			}

			@Override
			public boolean hasNext() {
				return this.iterator.hasNext() || this.index < this.objects.length;
			}

			@Override
			public T next() {
				if (this.iterator.hasNext()) {
					return this.iterator.next();
				}
				if (this.index < this.objects.length) {
					T n = this.objects[this.index];
					++this.index;
					return n;
				}
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		}

	}

}
