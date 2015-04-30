package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

public class FunctionalComponent extends ComponentInstance {

	private DirectionType direction;

	public enum DirectionType {
		INPUT("input"), OUTPUT("output"), INOUT("inout"), NONE("none");
		private final String directionType;

		DirectionType(String directionType) {
			this.directionType = directionType;
		}

		/**
		 * Returns the direction type.
		 * 
		 * @return direction type.
		 */
		public String getDirectionType() {
			return directionType;
		}

		@Override
		public String toString() {
			return directionType;
		}

		/**
		 * Convert the specified URI to its corresponding DirectionType instance.
		 * @param direction
		 * @return the corresponding DirectionType instance
		 */
		public static DirectionType convertToDirectionType(URI direction) {
			// codereview: direction is a URI but DirectionType is an enum -- the .equals should always be returning false
			if (direction != null) {
				if (direction.equals(DirectionType.INOUT)) {
					return DirectionType.INOUT;
				} else if (direction.equals(DirectionType.INPUT)) {
					return DirectionType.INPUT;
				} else if (direction.equals(DirectionType.NONE)) {
					return DirectionType.NONE;
				} else if (direction.equals(DirectionType.OUTPUT)) {
					return DirectionType.OUTPUT;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		/**
		 * Returns the direction type in URI.
		 * @return direction type in URI
		 */
		public static URI convertToURI(DirectionType direction) {
			if (direction != null) {
				if (direction.equals(DirectionType.INOUT)) {
					return Direction.inout;
				}
				else if (direction.equals(DirectionType.INPUT)) {
					return Direction.input;
				}
				else if (direction.equals(DirectionType.OUTPUT)) {
					return Direction.output;
				}
				else if (direction.equals(DirectionType.NONE)) {
					return Direction.none;
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}
	}
	

	public FunctionalComponent(URI identity, AccessType access, URI instantiatedComponent,
			DirectionType direction) {
		super(identity, access, instantiatedComponent);
		setDirection(direction);
	}
	
	private FunctionalComponent(FunctionalComponent functionalComponent) {
		super(functionalComponent);
		this.setDirection(functionalComponent.getDirection());
	}

	/**
	 * Returns field variable <code>direction</code> to the specified element.
	 * 
	 * @return field variable <code>direction</code> to the specified element
	 */
	public DirectionType getDirection() {
		return direction;
	}

	/**
	 * Returns the direction type in URI.
	 * 
	 * @return direction type in URI
	 */
	public URI getDirectionURI() {
		if (direction != null) {
			if (direction.equals(DirectionType.INOUT)) {
				return Direction.inout;
			} else if (direction.equals(DirectionType.INPUT)) {
				return Direction.input;
			} else if (direction.equals(DirectionType.OUTPUT)) {
				return Direction.output;
			} else if (direction.equals(DirectionType.NONE)) {
				return Direction.none;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Sets field variable <code>direction</code> to the specified element.
	 */
	public void setDirection(DirectionType direction) {
		if (direction==null) {
			throw new NullPointerException("Direction must not be null.");
		}
		this.direction = direction;
	}

	/**
	 * Sets field variable <code>direction</code> to the element corresponding
	 * to the specified URI.
	 */
	public void setDirection(URI direction) {
		if (direction != null && direction.equals(Direction.input)) {
			this.direction = DirectionType.INPUT;
		} else if (direction != null && direction.equals(Direction.output)) {
			this.direction = DirectionType.OUTPUT;
		} else if (direction != null && direction.equals(Direction.inout)) {
			this.direction = DirectionType.INOUT;
		} else if (direction != null && direction.equals(Direction.none)) {
			this.direction = DirectionType.NONE;
		} else {
			throw new IllegalArgumentException("Not a valid access type.");
		}
	}

	private static final class Direction {
		public static final URI	input	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "input");
		public static final URI	output	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "output");
		public static final URI	inout	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "inout");
		public static final URI	none	= URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "none");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
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
		FunctionalComponent other = (FunctionalComponent) obj;
		return direction == other.direction;
	}

	@Override
	protected FunctionalComponent deepCopy() {
		return new FunctionalComponent(this);
	}

	/**
	 * Assume this Component object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		if (!this.getMapsTos().isEmpty()) {
			// Update children's URIs
			for (MapsTo mapsTo : this.getMapsTos()) {
				mapsTo.updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
			}
		}
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);		
	}
}
