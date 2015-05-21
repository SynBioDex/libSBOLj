package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class Cut extends Location{
	
	private int at;
	Cut(URI identity, int at) {
		super(identity);
		setAt(at);
	}
	
	private Cut(Cut cut) {
		super(cut);
		this.setAt(cut.getAt());
	}

	/**
	 * Returns field variable <code>at</code>.
	 * @return field variable <code>at</code>
	 */
	public int getAt() {
		return at;
	}

	/**
	 * Sets field variable <code>at</code> to the specified element.
	 */
	public void setAt(int at) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (at<0) {
			throw new IllegalArgumentException("Cut "+this.getIdentity()+" must have a value greater than or equal to zero.");
		}
		this.at = at;
	}
	
	@Override
	protected Cut deepCopy() {
		return new Cut(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + at;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cut other = (Cut) obj;
		if (at != other.at)
			return false;
		return orientation == other.orientation;
	}
}
