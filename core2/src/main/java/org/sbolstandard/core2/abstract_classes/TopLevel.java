package org.sbolstandard.core2.abstract_classes;

import java.net.URI;

public abstract class TopLevel extends Documented{

	public TopLevel(URI identity) {
		super(identity);
	}

	public TopLevel(String authority, String id, String version) {
		super(authority, id, version);
	}

	protected TopLevel(TopLevel toplevel) {
		super(toplevel);
	}

	/**
	 * Get a deep copy of the object first, set its display ID to the specified value, and set the major version to "1" and minor version to "0".
	 * @param displayId
	 * @param version
	 * @return the copied {@link Documented} instance.
	 */
	public TopLevel copy(String displayId, String version) {
		TopLevel cloned = (TopLevel) this.deepCopy();
		cloned.setWasDerivedFrom(this.getIdentity());
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		return cloned;
	}

	/**
	 * Get a deep copy the object first, increment the major version of the original object by 1,
	 * and then set this value to the major version of the deepCopyd object. Set the minor version of the deepCopyd object to 0.
	 * Update the URI of the deepCopyd object.
	 * @param version
	 * @return the copied object
	 */

	public TopLevel newVersion(String version) {
		TopLevel cloned = (TopLevel) this.deepCopy();
		cloned.setWasDerivedFrom(this.getIdentity());
		cloned.setVersion(version);

		return cloned;
	}
}
