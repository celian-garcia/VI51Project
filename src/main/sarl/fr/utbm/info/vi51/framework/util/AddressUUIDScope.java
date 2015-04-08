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

import io.sarl.lang.core.Address;
import io.sarl.lang.core.Scope;

import java.util.UUID;

/** This scope is accepting the addresses that has the agent with the given identifier.
 * 
 * @author $Author: galland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class AddressUUIDScope implements Scope<Address> {
	
	private static final long serialVersionUID = -854440717352244448L;

	private static final String SCOPE_ID = "uuid://"; //$NON-NLS-1$

	private final UUID id;

	/**
	 * @param id - identifier to put in the scope.
	 */
	public AddressUUIDScope(UUID id) {
	    this.id = id;
	}

	@Override
	public String toString() {
		return SCOPE_ID + this.id.toString();
	}

	@Override
	public boolean matches(Address address) {
		assert (address != null);
		return this.id.equals(address.getUUID());
	}
	
}
